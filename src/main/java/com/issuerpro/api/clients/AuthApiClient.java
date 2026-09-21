package com.issuerpro.api.clients;

import com.issuerpro.api.ApiEndpoints;
import com.issuerpro.api.ApiSpecFactory;
import com.issuerpro.api.models.LoginRequest;
import com.issuerpro.config.ConfigManager;
import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

import static io.restassured.RestAssured.given;

/**
 * Client for /auth endpoints.
 */
public class AuthApiClient {

    private static final Logger logger = LoggerFactory.getLogger(AuthApiClient.class);

    public Response login(String username, String password) {
        logger.info("Executing API login for username: {}", username);
        LoginRequest request = new LoginRequest(username, password);

        return given()
                .spec(ApiSpecFactory.defaultRequestSpec())
                .body(request)
                .when()
                .post(ApiEndpoints.AUTH_LOGIN);
    }

    public Response loginAsAdmin() {
        return login(ConfigManager.get("admin.username", "admin"),
                     ConfigManager.get("admin.password", "Admin@123"));
    }

    public Response loginAsOps() {
        return login(ConfigManager.get("ops.username", "operations"),
                     ConfigManager.get("ops.password", "Ops@123"));
    }

    public Response loginAsCsAgent() {
        return login(ConfigManager.get("cs.username", "csagent"),
                     ConfigManager.get("cs.password", "Cs@123"));
    }

    public Response loginAsSupport() {
        return login(ConfigManager.get("support.username", "support"),
                     ConfigManager.get("support.password", "Support@123"));
    }

    public Response getCurrentUser(Map<String, String> cookies) {
        logger.info("Executing API GET /auth/me");
        return given()
                .spec(ApiSpecFactory.authenticatedRequestSpec(cookies))
                .when()
                .get(ApiEndpoints.AUTH_ME);
    }

    public Response logout(Map<String, String> cookies) {
        logger.info("Executing API POST /auth/logout");
        return given()
                .spec(ApiSpecFactory.authenticatedRequestSpec(cookies))
                .when()
                .post(ApiEndpoints.AUTH_LOGOUT);
    }
}
