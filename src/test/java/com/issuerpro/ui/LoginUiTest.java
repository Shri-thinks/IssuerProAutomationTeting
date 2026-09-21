package com.issuerpro.ui;

import com.issuerpro.pages.DashboardPage;
import com.issuerpro.pages.LoginPage;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LoginUiTest extends BaseUiTest {

    @Test(description = "Verify successful login with valid Admin credentials and subsequent logout")
    public void testAdminLoginAndLogout() {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.open();

        assertThat(loginPage.isLoginFormDisplayed())
                .as("Login form should be visible")
                .isTrue();

        DashboardPage dashboardPage = loginPage.login("admin", "Admin@123");

        assertThat(dashboardPage.isDashboardLoaded())
                .as("Dashboard should load after valid login")
                .isTrue();

        assertThat(dashboardPage.getTopbarRole())
                .as("Topbar role badge should show ADMIN")
                .isEqualTo("ADMIN");

        LoginPage postLogoutPage = dashboardPage.logout();
        assertThat(postLogoutPage.isLoginFormDisplayed())
                .as("Should redirect back to login page after logout")
                .isTrue();
    }

    @Test(description = "Verify that invalid credentials display an appropriate error message")
    public void testInvalidLoginShowsErrorMessage() {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.open();

        loginPage.loginExpectingFailure("invalid_user", "WrongPassword123!");

        assertThat(loginPage.isErrorMessageDisplayed())
                .as("Error message banner should be displayed on invalid credentials")
                .isTrue();

        assertThat(loginPage.getErrorMessage())
                .as("Error message should describe authentication failure")
                .isNotEmpty();
    }

    @Test(description = "Verify demo role quick fill button populates input fields")
    public void testDemoButtonAutofill() {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.open();

        loginPage.clickDemoAdmin();

        assertThat(loginPage.getUsernameValue())
                .as("Admin demo button should autofill 'admin'")
                .isEqualTo("admin");

        assertThat(loginPage.getPasswordValue())
                .as("Admin demo button should autofill password")
                .isEqualTo("Admin@123");
    }

    @DataProvider(name = "roleCredentials")
    public Object[][] roleCredentials() {
        return new Object[][]{
                {"operations", "Ops@123", "OPERATIONS"},
                {"csagent", "Cs@123", "CUSTOMER_SERVICE"},
                {"support", "Support@123", "SUPPORT"}
        };
    }

    @Test(dataProvider = "roleCredentials", description = "Verify login for multiple platform roles")
    public void testMultiRoleLogin(String username, String password, String expectedRole) {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.open();

        DashboardPage dashboardPage = loginPage.login(username, password);

        assertThat(dashboardPage.isDashboardLoaded())
                .as("Dashboard should be loaded for role: " + expectedRole)
                .isTrue();

        assertThat(dashboardPage.getTopbarRole())
                .as("Topbar role should match expected role")
                .isEqualTo(expectedRole);
    }
}
