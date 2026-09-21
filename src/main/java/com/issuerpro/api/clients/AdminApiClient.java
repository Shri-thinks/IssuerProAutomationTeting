package com.issuerpro.api.clients;

import com.issuerpro.api.ApiEndpoints;
import com.issuerpro.api.ApiSpecFactory;
import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

import static io.restassured.RestAssured.given;

/**
 * Client for /admin endpoints (test environment reset, fraud playground).
 */
public class AdminApiClient {

    private static final Logger logger = LoggerFactory.getLogger(AdminApiClient.class);

    public Response resetTestData(Map<String, String> cookies) {
        logger.info("Executing API POST /admin/reset (reseeding test baseline)");
        return given()
                .spec(ApiSpecFactory.authenticatedRequestSpec(cookies))
                .when()
                .post(ApiEndpoints.ADMIN_RESET);
    }

    public Response getFraudConfig(Map<String, String> cookies) {
        logger.info("Executing API GET /admin/fraud-config");
        return given()
                .spec(ApiSpecFactory.authenticatedRequestSpec(cookies))
                .when()
                .get(ApiEndpoints.ADMIN_FRAUD_CONFIG);
    }

    public Response resetFraudConfig(Map<String, String> cookies) {
        logger.info("Executing API POST /admin/fraud-config/reset");
        return given()
                .spec(ApiSpecFactory.authenticatedRequestSpec(cookies))
                .when()
                .post(ApiEndpoints.ADMIN_FRAUD_CONFIG_RESET);
    }
}
