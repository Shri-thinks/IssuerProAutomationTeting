package com.issuerpro.listeners;

import com.issuerpro.driver.DriverManager;
import com.issuerpro.utils.ScreenshotUtil;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

/**
 * TestNG listener to log lifecycle events and capture screenshots on UI test failures.
 */
public class TestListener implements ITestListener {

    private static final Logger logger = LoggerFactory.getLogger(TestListener.class);

    @Override
    public void onStart(ITestContext context) {
        logger.info("==================================================================");
        logger.info("TEST SUITE STARTED: {}", context.getName());
        logger.info("==================================================================");
    }

    @Override
    public void onFinish(ITestContext context) {
        logger.info("==================================================================");
        logger.info("TEST SUITE FINISHED: {}", context.getName());
        logger.info("Passed: {}, Failed: {}, Skipped: {}",
                context.getPassedTests().size(),
                context.getFailedTests().size(),
                context.getSkippedTests().size());
        logger.info("==================================================================");
    }

    @Override
    public void onTestStart(ITestResult result) {
        logger.info(">>> STARTING TEST: {}.{}()", result.getTestClass().getName(), result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        logger.info("+++ PASSED TEST: {}.{}() [{} ms]",
                result.getTestClass().getName(), result.getName(),
                (result.getEndMillis() - result.getStartMillis()));
    }

    @Override
    public void onTestFailure(ITestResult result) {
        logger.error("--- FAILED TEST: {}.{}()", result.getTestClass().getName(), result.getName(), result.getThrowable());

        WebDriver driver = DriverManager.getDriver();
        if (driver != null) {
            String screenshotPath = ScreenshotUtil.takeScreenshot(driver, result.getName());
            if (screenshotPath != null) {
                logger.info("Failure screenshot captured: {}", screenshotPath);
            }
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        logger.warn("*** SKIPPED TEST: {}.{}()", result.getTestClass().getName(), result.getName());
    }
}
