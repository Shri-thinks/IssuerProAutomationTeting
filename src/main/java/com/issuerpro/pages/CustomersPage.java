package com.issuerpro.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Page Object for the Customer Directory page (/customers).
 */
public class CustomersPage extends BasePage {

    // Customer Directory elements
    private final By pageTitle = By.cssSelector("[data-testid='customers-title']");
    private final By searchInput = By.cssSelector("[data-testid='customers-search-input']");
    private final By customersTable = By.cssSelector("[data-testid='customers-table']");
    private final By tableRows = By.cssSelector("[data-testid^='customers-row-']");
    private final By refreshButton = By.cssSelector("[data-testid='customers-refresh-button']");

    // Create Customer Dialog elements
    private final By createCustomerTrigger = By.cssSelector("[data-testid='create-customer-trigger']");
    private final By firstNameInput = By.cssSelector("[data-testid='customer-first_name-input']");
    private final By lastNameInput = By.cssSelector("[data-testid='customer-last_name-input']");
    private final By emailInput = By.cssSelector("[data-testid='customer-email-input']");
    private final By phoneInput = By.cssSelector("[data-testid='customer-phone-input']");
    private final By addressInput = By.cssSelector("[data-testid='customer-address-input']");
    private final By cityInput = By.cssSelector("[data-testid='customer-city-input']");
    private final By stateInput = By.cssSelector("[data-testid='customer-state-input']");
    private final By submitButton = By.cssSelector("[data-testid='create-customer-submit']");
    private final By cancelButton = By.cssSelector("[data-testid='create-customer-cancel']");

    public CustomersPage(WebDriver driver) {
        super(driver);
    }

    public boolean isPageLoaded() {
        return isDisplayed(pageTitle) && isDisplayed(customersTable);
    }

    public String getPageTitle() {
        return getText(pageTitle);
    }

    public CustomersPage search(String query) {
        logger.info("Searching customer directory for: '{}'", query);
        sendKeys(searchInput, query);
        return this;
    }

    public int getCustomerRowCount() {
        waitForVisibility(customersTable);
        List<WebElement> rows = findElements(tableRows);
        return rows.size();
    }

    public CustomersPage openCreateCustomerDialog() {
        logger.info("Opening Create Customer modal");
        click(createCustomerTrigger);
        waitForVisibility(firstNameInput);
        return this;
    }

    public CustomersPage fillCustomerForm(String firstName, String lastName, String email, String phone,
                                          String address, String city, String state) {
        logger.info("Filling customer form for: {} {}", firstName, lastName);
        sendKeys(firstNameInput, firstName);
        sendKeys(lastNameInput, lastName);
        sendKeys(emailInput, email);
        sendKeys(phoneInput, phone);
        sendKeys(addressInput, address);
        sendKeys(cityInput, city);
        sendKeys(stateInput, state);
        return this;
    }

    public CustomersPage submitCustomerForm() {
        logger.info("Submitting customer form");
        click(submitButton);
        return this;
    }

    public CustomersPage cancelCustomerForm() {
        click(cancelButton);
        return this;
    }
}
