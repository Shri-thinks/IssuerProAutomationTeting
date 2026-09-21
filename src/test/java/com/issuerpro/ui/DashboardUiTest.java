package com.issuerpro.ui;

import com.issuerpro.pages.DashboardPage;
import com.issuerpro.pages.LoginPage;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DashboardUiTest extends BaseUiTest {

    @Test(description = "Verify that dashboard elements and navigation widgets are rendered properly")
    public void testDashboardOverviewWidgets() {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.open();
        DashboardPage dashboardPage = loginPage.login("admin", "Admin@123");

        assertThat(dashboardPage.isDashboardLoaded())
                .as("Dashboard should load successfully")
                .isTrue();

        assertThat(dashboardPage.isSidebarDisplayed())
                .as("Sidebar navigation should be visible")
                .isTrue();

        assertThat(dashboardPage.isRecentTransactionsDisplayed())
                .as("Recent transactions widget should be visible on dashboard")
                .isTrue();

        assertThat(dashboardPage.getDashboardTitle())
                .as("Dashboard title should not be empty")
                .isNotEmpty();
    }
}
