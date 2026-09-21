package com.issuerpro.ui;

import com.issuerpro.pages.CustomersPage;
import com.issuerpro.pages.DashboardPage;
import com.issuerpro.pages.LoginPage;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CustomersUiTest extends BaseUiTest {

    @Test(description = "Verify Customer Directory navigation and table display")
    public void testCustomerDirectoryTableAndSearch() {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.open();
        DashboardPage dashboardPage = loginPage.login("admin", "Admin@123");

        CustomersPage customersPage = dashboardPage.goToCustomers();

        assertThat(customersPage.isPageLoaded())
                .as("Customers page and table should be displayed")
                .isTrue();

        assertThat(customersPage.getPageTitle())
                .as("Customers page title should indicate Customer Directory")
                .containsIgnoringCase("Customer");

        int initialCount = customersPage.getCustomerRowCount();
        assertThat(initialCount)
                .as("Customer table should have seeded customer records")
                .isGreaterThan(0);

        // Perform search
        customersPage.search("admin");
    }
}
