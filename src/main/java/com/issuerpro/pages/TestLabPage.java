package com.issuerpro.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Page Object for Automation Test Lab (/test-lab).
 */
public class TestLabPage extends BasePage {

    private final By title = By.cssSelector("[data-testid='test-lab-title']");
    private final By resetTriggerBtn = By.cssSelector("[data-testid='reset-data-trigger']");
    private final By resetDialog = By.cssSelector("[data-testid='reset-data-dialog']");
    private final By resetConfirmBtn = By.cssSelector("[data-testid='reset-data-confirm']");
    private final By resetCancelBtn = By.cssSelector("[data-testid='reset-data-cancel']");
    private final By resetResultPanel = By.cssSelector("[data-testid='reset-result-panel']");
    private final By resetResultMessage = By.cssSelector("[data-testid='reset-result-message']");

    private final By fraudConfigPanel = By.cssSelector("[data-testid='fraud-config-panel']");
    private final By fraudRestoreDefaultsBtn = By.cssSelector("[data-testid='fraud-restore-defaults-button']");

    public TestLabPage(WebDriver driver) {
        super(driver);
    }

    public boolean isPageLoaded() {
        return isDisplayed(title) && isDisplayed(resetTriggerBtn);
    }

    public String getPageTitle() {
        return getText(title);
    }

    public TestLabPage triggerDataReset() {
        logger.info("Triggering data reset from UI");
        click(resetTriggerBtn);
        waitForVisibility(resetDialog);
        click(resetConfirmBtn);
        waitForVisibility(resetResultPanel);
        return this;
    }

    public String getResetResultMessage() {
        return getText(resetResultMessage);
    }

    public boolean isFraudPanelDisplayed() {
        return isDisplayed(fraudConfigPanel);
    }
}
