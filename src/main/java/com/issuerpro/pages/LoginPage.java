package com.issuerpro.pages;

import com.issuerpro.config.ConfigManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Page Object for IssuerPro Secure Sign-In Page (/login).
 */
public class LoginPage extends BasePage {

    // Locators using application data-testid attributes
    private final By loginForm = By.cssSelector("[data-testid='login-form']");
    private final By usernameInput = By.cssSelector("[data-testid='login-username-input']");
    private final By passwordInput = By.cssSelector("[data-testid='login-password-input']");
    private final By submitButton = By.cssSelector("[data-testid='login-submit-button']");
    private final By errorMessage = By.cssSelector("[data-testid='login-error-message']");

    // Demo role buttons
    private final By demoAdminBtn = By.cssSelector("[data-testid='login-demo-admin']");
    private final By demoOpsBtn = By.cssSelector("[data-testid='login-demo-operations']");
    private final By demoCsBtn = By.cssSelector("[data-testid='login-demo-customer_service']");
    private final By demoSupportBtn = By.cssSelector("[data-testid='login-demo-support']");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public LoginPage open() {
        String url = ConfigManager.getLoginUrl();
        logger.info("Opening Login page at: {}", url);
        driver.get(url);
        waitForVisibility(loginForm);
        return this;
    }

    public LoginPage enterUsername(String username) {
        sendKeys(usernameInput, username);
        return this;
    }

    public LoginPage enterPassword(String password) {
        sendKeys(passwordInput, password);
        return this;
    }

    public void clickSubmit() {
        click(submitButton);
    }

    public DashboardPage login(String username, String password) {
        logger.info("Logging in with username: {}", username);
        enterUsername(username);
        enterPassword(password);
        clickSubmit();
        return new DashboardPage(driver);
    }

    public LoginPage loginExpectingFailure(String username, String password) {
        logger.info("Attempting invalid login with username: {}", username);
        enterUsername(username);
        enterPassword(password);
        clickSubmit();
        waitForVisibility(errorMessage);
        return this;
    }

    public LoginPage clickDemoAdmin() {
        logger.info("Clicking Demo Admin quick fill button");
        click(demoAdminBtn);
        return this;
    }

    public LoginPage clickDemoOperations() {
        logger.info("Clicking Demo Operations quick fill button");
        click(demoOpsBtn);
        return this;
    }

    public LoginPage clickDemoCustomerService() {
        logger.info("Clicking Demo Customer Service quick fill button");
        click(demoCsBtn);
        return this;
    }

    public LoginPage clickDemoSupport() {
        logger.info("Clicking Demo Support quick fill button");
        click(demoSupportBtn);
        return this;
    }

    public String getUsernameValue() {
        return waitForVisibility(usernameInput).getAttribute("value");
    }

    public String getPasswordValue() {
        return waitForVisibility(passwordInput).getAttribute("value");
    }

    public String getErrorMessage() {
        return getText(errorMessage);
    }

    public boolean isErrorMessageDisplayed() {
        return isDisplayed(errorMessage);
    }

    public boolean isLoginFormDisplayed() {
        return isDisplayed(loginForm);
    }
}
