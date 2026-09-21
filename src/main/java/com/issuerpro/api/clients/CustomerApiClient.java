package com.issuerpro.api.clients;

import com.issuerpro.api.ApiEndpoints;
import com.issuerpro.api.ApiSpecFactory;
import com.issuerpro.api.models.CustomerRequest;
import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

import static io.restassured.RestAssured.given;

/**
 * Client for /customers endpoints.
 */
public class CustomerApiClient {

    private static final Logger logger = LoggerFactory.getLogger(CustomerApiClient.class);

    public Response getCustomers(Map<String, String> cookies) {
        logger.info("Executing API GET /customers");
        return given()
                .spec(ApiSpecFactory.authenticatedRequestSpec(cookies))
                .when()
                .get(ApiEndpoints.CUSTOMERS);
    }

    public Response getCustomers(Map<String, String> cookies, Map<String, ?> queryParams) {
        logger.info("Executing API GET /customers with query params: {}", queryParams);
        return given()
                .spec(ApiSpecFactory.authenticatedRequestSpec(cookies))
                .queryParams(queryParams)
                .when()
                .get(ApiEndpoints.CUSTOMERS);
    }

    public Response createCustomer(Map<String, String> cookies, CustomerRequest request) {
        logger.info("Executing API POST /customers for: {} {}", request.getFirst_name(), request.getLast_name());
        return given()
                .spec(ApiSpecFactory.authenticatedRequestSpec(cookies))
                .body(request)
                .when()
                .post(ApiEndpoints.CUSTOMERS);
    }
}
