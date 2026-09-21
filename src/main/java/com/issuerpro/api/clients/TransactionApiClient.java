package com.issuerpro.api.clients;

import com.issuerpro.api.ApiEndpoints;
import com.issuerpro.api.ApiSpecFactory;
import com.issuerpro.api.models.PurchaseRequest;
import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

import static io.restassured.RestAssured.given;

/**
 * Client for /transactions and /dashboard endpoints.
 */
public class TransactionApiClient {

    private static final Logger logger = LoggerFactory.getLogger(TransactionApiClient.class);

    public Response getTransactions(Map<String, String> cookies) {
        logger.info("Executing API GET /transactions");
        return given()
                .spec(ApiSpecFactory.authenticatedRequestSpec(cookies))
                .when()
                .get(ApiEndpoints.TRANSACTIONS);
    }

    public Response createPurchase(Map<String, String> cookies, PurchaseRequest request) {
        logger.info("Executing API POST /transactions/purchase for card: {}, amount: {}", request.getCard_id(), request.getAmount());
        return given()
                .spec(ApiSpecFactory.authenticatedRequestSpec(cookies))
                .body(request)
                .when()
                .post(ApiEndpoints.TRANSACTIONS_PURCHASE);
    }

    public Response getDashboardStats(Map<String, String> cookies) {
        logger.info("Executing API GET /dashboard/stats");
        return given()
                .spec(ApiSpecFactory.authenticatedRequestSpec(cookies))
                .when()
                .get(ApiEndpoints.DASHBOARD_STATS);
    }
}
