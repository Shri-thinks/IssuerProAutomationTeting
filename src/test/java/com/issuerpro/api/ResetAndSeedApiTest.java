package com.issuerpro.api;

import com.issuerpro.api.models.ResetResponse;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class ResetAndSeedApiTest extends BaseApiTest {

    @Test(description = "Verify reseeding test database via POST /admin/reset")
    public void testResetAndSeedDatabase() {
        Map<String, String> cookies = getAdminCookies();

        Response response = adminApiClient.resetTestData(cookies);

        assertThat(response.getStatusCode())
                .as("POST /admin/reset should return HTTP 200")
                .isEqualTo(200);

        ResetResponse resetResponse = response.as(ResetResponse.class);
        assertThat(resetResponse.getMessage())
                .as("Reset response should contain confirmation message")
                .isNotEmpty();

        assertThat(resetResponse.getCollections())
                .as("Collections count should contain customers, cards, accounts")
                .containsKeys("customers", "cards", "accounts", "transactions");
    }
}
