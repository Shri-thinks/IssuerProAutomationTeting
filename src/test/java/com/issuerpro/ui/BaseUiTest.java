package com.issuerpro.ui;

import com.issuerpro.driver.DriverFactory;
import com.issuerpro.driver.DriverManager;
import com.issuerpro.listeners.TestListener;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

/**
 * Base class for all Selenium UI tests.
 * Sets up and tears down thread-safe WebDriver for each test method.
 */
@Listeners(TestListener.class)
public abstract class BaseUiTest {

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        WebDriver driver = DriverFactory.createDriver();
        DriverManager.setDriver(driver);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        DriverManager.quitDriver();
    }

    protected WebDriver getDriver() {
        return DriverManager.getDriver();
    }
}
