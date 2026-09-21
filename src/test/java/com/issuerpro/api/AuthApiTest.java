package com.issuerpro.api;

import com.issuerpro.api.models.LoginResponse;
import io.restassured.response.Response;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class AuthApiTest extends BaseApiTest {

    @Test(description = "Verify successful API authentication for Admin role")
    public void testAdminLoginSuccess() {
        Response response = authApiClient.loginAsAdmin();

        assertThat(response.getStatusCode())
                .as("Login status code should be 200")
                .isEqualTo(200);

        LoginResponse loginResponse = response.as(LoginResponse.class);
        assertThat(loginResponse.getUser())
                .as("Response should contain user details")
                .isNotNull();

        assertThat(loginResponse.getUser().getRole())
                .as("User role should be ADMIN")
                .isEqualTo("ADMIN");

        assertThat(loginResponse.getUser().getUsername())
                .as("Username should be admin")
                .isEqualTo("admin");
    }

    @Test(description = "Verify that invalid credentials return HTTP 401 Unauthorized")
    public void testInvalidLoginReturns401() {
        Response response = authApiClient.login("fake_admin", "WrongPassword!");

        assertThat(response.getStatusCode())
                .as("Invalid login should return 401 Unauthorized")
                .isEqualTo(401);
    }

    @Test(description = "Verify that /auth/me returns the authenticated user session")
    public void testGetCurrentUser() {
        Response loginResp = authApiClient.loginAsAdmin();
        assertThat(loginResp.getStatusCode()).isEqualTo(200);

        Map<String, String> cookies = loginResp.getCookies();

        Response meResp = authApiClient.getCurrentUser(cookies);
        assertThat(meResp.getStatusCode())
                .as("Status code of /auth/me should be 200")
                .isEqualTo(200);

        String role = meResp.jsonPath().getString("role");
        assertThat(role).isEqualTo("ADMIN");
    }

    @DataProvider(name = "apiRoleUsers")
    public Object[][] apiRoleUsers() {
        return new Object[][]{
                {"operations", "Ops@123", "OPERATIONS"},
                {"csagent", "Cs@123", "CUSTOMER_SERVICE"},
                {"support", "Support@123", "SUPPORT"}
        };
    }

    @Test(dataProvider = "apiRoleUsers", description = "Verify authentication across all configured roles")
    public void testAllRolesLogin(String username, String password, String expectedRole) {
        Response response = authApiClient.login(username, password);

        assertThat(response.getStatusCode())
                .as("Login should succeed with 200 for user: " + username)
                .isEqualTo(200);

        String role = response.jsonPath().getString("user.role");
        assertThat(role)
                .as("Role in response should match expected")
                .isEqualTo(expectedRole);
    }
}
