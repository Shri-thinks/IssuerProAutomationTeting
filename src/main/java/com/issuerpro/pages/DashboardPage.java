package com.issuerpro.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Page Object for Processor Command Dashboard (/).
 */
public class DashboardPage extends BasePage {

    // Topbar
    private final By topbarUsername = By.cssSelector("[data-testid='topbar-username']");
    private final By topbarRole = By.cssSelector("[data-testid='topbar-role']");
    private final By logoutButton = By.cssSelector("[data-testid='logout-button']");

    // Sidebar navigation
    private final By appSidebar = By.cssSelector("[data-testid='app-sidebar']");
    private final By navDashboard = By.cssSelector("[data-testid='nav-dashboard']");
    private final By navCustomers = By.cssSelector("[data-testid='nav-customers']");
    private final By navAccounts = By.cssSelector("[data-testid='nav-accounts']");
    private final By navCards = By.cssSelector("[data-testid='nav-cards']");
    private final By navTransactions = By.cssSelector("[data-testid='nav-transactions']");
    private final By navTestLab = By.cssSelector("[data-testid='nav-test-lab']");

    // Main Dashboard widgets
    private final By dashboardTitle = By.cssSelector("[data-testid='dashboard-title']");
    private final By dashboardKpis = By.cssSelector("[data-testid='dashboard-kpis']");
    private final By recentTransactions = By.cssSelector("[data-testid='dashboard-recent-transactions']");

    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    public boolean isDashboardLoaded() {
        return isDisplayed(dashboardTitle) && isDisplayed(dashboardKpis);
    }

    public String getDashboardTitle() {
        return getText(dashboardTitle);
    }

    public String getTopbarUsername() {
        return getText(topbarUsername);
    }

    public String getTopbarRole() {
        return getText(topbarRole);
    }

    public CustomersPage goToCustomers() {
        logger.info("Navigating to Customers page via sidebar");
        click(navCustomers);
        return new CustomersPage(driver);
    }

    public TestLabPage goToTestLab() {
        logger.info("Navigating to Automation Test Lab page via sidebar");
        click(navTestLab);
        return new TestLabPage(driver);
    }

    public LoginPage logout() {
        logger.info("Logging out from the platform");
        click(logoutButton);
        return new LoginPage(driver);
    }

    public boolean isSidebarDisplayed() {
        return isDisplayed(appSidebar);
    }

    public boolean isRecentTransactionsDisplayed() {
        return isDisplayed(recentTransactions);
    }
}
