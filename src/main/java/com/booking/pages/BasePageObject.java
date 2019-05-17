package com.booking.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static java.lang.Math.random;

public abstract class BasePageObject {

    private static final int DEFAULT_TIMEOUT = 10;

    WebDriver driver;
    Logger log;

    BasePageObject(WebDriver driver, Logger log) {
        this.driver = driver;
        this.log = log;
    }

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
     * Check that opened right page
     */
    public abstract void isOpenedRightPage();

    /**
     * Wait for the element to present
     */
    protected void waitForElementPresent(By locator) {
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
        ((JavascriptExecutor) driver).executeScript("arguments[0].style.border='3px solid green'",
                driver.findElement(locator));
    }

    /**
     * Unhighlight the element
     */
    private void unhighlightElement(By locator) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].style.border='0px'",
                driver.findElement(locator));
    }

    /**
     * Find element using given locator
     */
    protected WebElement find(By locator) {
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
        log.info(String.format("Clicking element, located: '%s'", locator));
        highlightElement(locator);
        find(locator).click();
        unhighlightElement(locator);
    }

    /**
     * Type given text into element with given locator when its visible
     */
    protected void type(String text, By locator) {
        log.info(String.format("Typing text '%s' into text box, located: '%s'", text, locator));
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

    protected static int set_iRandom_number(int iFrom, int iTo) {
        return iFrom + (int) (random() * iTo);
    }

    protected void selectDropdownByValue(By locator, int value) {
        log.info(String.format("Selecting dropdown value '%s' ", value));
        WebElement dropdownElement = find(locator);
        Select dropdown = new Select(dropdownElement);
        dropdown.selectByValue(String.format("%d", value));
    }
}
