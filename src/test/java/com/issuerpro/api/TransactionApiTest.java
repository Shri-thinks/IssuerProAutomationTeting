package com.issuerpro.api;

import com.issuerpro.api.models.PurchaseRequest;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

public class TransactionApiTest extends BaseApiTest {

    @Test(description = "Verify retrieving dashboard summary statistics via GET /dashboard/stats")
    public void testGetDashboardStats() {
        Map<String, String> cookies = getAdminCookies();

        Response response = transactionApiClient.getDashboardStats(cookies);

        assertThat(response.getStatusCode())
                .as("GET /dashboard/stats should return HTTP 200")
                .isEqualTo(200);
    }

    @Test(description = "Verify retrieving transaction feed via GET /transactions")
    public void testGetTransactions() {
        Map<String, String> cookies = getAdminCookies();

        Response response = transactionApiClient.getTransactions(cookies);

        assertThat(response.getStatusCode())
                .as("GET /transactions should return HTTP 200")
                .isEqualTo(200);

        int txCount = response.jsonPath().getInt("items.size()");
        assertThat(txCount)
                .as("Should return seeded transaction records")
                .isGreaterThanOrEqualTo(1);
    }

    @Test(description = "Verify submitting a purchase transaction to the authorization engine")
    public void testPurchaseAuthorization() {
        Map<String, String> cookies = getAdminCookies();

        // 1. Fetch available cards to get a valid card_id
        Response cardsResp = given()
                .spec(com.issuerpro.api.ApiSpecFactory.authenticatedRequestSpec(cookies))
                .when()
                .get(com.issuerpro.api.ApiEndpoints.CARDS);

        assertThat(cardsResp.getStatusCode()).isEqualTo(200);
        String cardId = cardsResp.jsonPath().getString("items[0].id");
        assertThat(cardId).as("Card ID should be present").isNotEmpty();

        // 2. Submit purchase
        PurchaseRequest purchase = new PurchaseRequest(
                cardId,
                25.50,
                "Automation Test Merchant",
                "GROCERY",
                false
        );

        Response purchaseResp = transactionApiClient.createPurchase(cookies, purchase);

        assertThat(purchaseResp.getStatusCode())
                .as("POST /transactions/purchase should return HTTP 200 or 201")
                .isIn(200, 201);
    }
}
