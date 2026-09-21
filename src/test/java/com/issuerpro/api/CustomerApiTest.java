package com.issuerpro.api;

import com.issuerpro.api.models.CustomerRequest;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.Map;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class CustomerApiTest extends BaseApiTest {

    @Test(description = "Verify retrieving customer list via GET /customers")
    public void testGetCustomers() {
        Map<String, String> cookies = getAdminCookies();

        Response response = customerApiClient.getCustomers(cookies);

        assertThat(response.getStatusCode())
                .as("GET /customers should return HTTP 200")
                .isEqualTo(200);

        int count = response.jsonPath().getInt("items.size()");
        assertThat(count)
                .as("Customer list should contain baseline seeded records")
                .isGreaterThanOrEqualTo(1);
    }

    @Test(description = "Verify onboarding a new customer via POST /customers")
    public void testCreateCustomer() {
        Map<String, String> cookies = getAdminCookies();

        String uniqueSuffix = UUID.randomUUID().toString().substring(0, 8);
        CustomerRequest request = new CustomerRequest(
                "TestFirst_" + uniqueSuffix,
                "TestLast_" + uniqueSuffix,
                "user_" + uniqueSuffix + "@example.com",
                "+1555" + (int)(Math.random() * 899999 + 100000),
                "1990-01-01",
                "123 Quality Assurance Blvd",
                "Austin",
                "TX",
                "USA"
        );

        Response response = customerApiClient.createCustomer(cookies, request);

        assertThat(response.getStatusCode())
                .as("POST /customers should return HTTP 200 or 201")
                .isIn(200, 201);

        String createdId = response.jsonPath().getString("id");
        assertThat(createdId)
                .as("Created customer should have a valid ID")
                .isNotEmpty();
    }
}
