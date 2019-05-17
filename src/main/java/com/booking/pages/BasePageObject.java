package com.booking.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public abstract class BasePageObject {

    private static final int DEFAULT_TIMEOUT = 10;

    private WebDriver driver;
    Logger log;

    BasePageObject(WebDriver driver, Logger log) {
        this.driver = driver;
        this.log = log;
    }

    /**
     * Open page with given URL
     */
    public abstract void openPage();

    /**
     * Open page with given URL
     */
    void openUrl(String url) {
        driver.get(url);
    }

    /**
     * Check that element present
     */
    boolean isElementPresent(By locator) {
        return !driver.findElements(locator).isEmpty();
    }

    /**
     * Wait for the element to present
     */
    private void waitForElementPresent(By locator) {
        new WebDriverWait(driver, DEFAULT_TIMEOUT).until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
    }

    /**
     * Wait for the element to be visible
     */
    protected void waitForElementVisible(By locator) {
        new WebDriverWait(driver, DEFAULT_TIMEOUT).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
    }

    /**
     * Wait for the element to be enabled
     */
    protected void waitForElementEnabled(By locator) {
        new WebDriverWait(driver, DEFAULT_TIMEOUT).until(ExpectedConditions.elementToBeClickable(locator));
    }

    /**
     * Highlight the element
     */
    private void highlightElement(By locator) {
        ((JavascriptExecutor) driver).executeScript("\"arguments[0].style.border='3px solid green'",
                driver.findElement(locator));
    }

    /**
     * Unhighlight the element
     */
    private void unhighlightElement(By locator) {
        ((JavascriptExecutor) driver).executeScript("\"arguments[0].style.border='0px'",
                driver.findElement(locator));
    }

    /**
     * Find element using given locator
     */
    private WebElement find(By locator) {
        waitForElementPresent(locator);
        return driver.findElement(locator);
    }

    /**
     * Find all elements using given locator
     */
    protected List<WebElement> findAll(By locator) {
        return driver.findElements(locator);
    }

    /**
     * Click on element with given locator when its visible
     */
    protected void click(By locator) {
        log.info(String.format("Clicking element '%s', located: '%s'", driver.findElement(locator).getText(), locator));
        highlightElement(locator);
        find(locator).click();
        unhighlightElement(locator);
    }

    /**
     * Type given text into element with given locator when its visible
     */
    protected void type(String text, By locator) {
        highlightElement(locator);
        find(locator).sendKeys(text);
        unhighlightElement(locator);
    }

    /**
     * Get URL of current page from browser
     */
    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
}
