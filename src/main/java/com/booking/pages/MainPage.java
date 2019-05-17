package com.booking.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import static com.booking.utilities.PropertyManager.getProperty;

public class MainPage extends BasePageObject {

    private static final String BASE_URL = getProperty("main.page.url");

    private By BASE_ELEMENT = By.id("logo_no_globe_new_logo");

    public MainPage(WebDriver driver, Logger log) {
        super(driver, log);
    }

    /**
     * Open MainPage with it's url
     */
    @Override
    public void openPage() {
        log.info(String.format("Opening page: %s", BASE_URL));
        openUrl(BASE_URL);
        Assert.assertTrue(isElementPresent(BASE_ELEMENT));
        log.info("Main page opened!");
    }
}
