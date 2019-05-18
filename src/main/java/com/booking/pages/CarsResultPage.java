package com.booking.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class CarsResultPage extends BasePageObject {

    private By BASE_ELEMENT = By.id("searchAgain");

    CarsResultPage(WebDriver driver, Logger log) {
        super(driver, log);
    }

    @Override
    public void isOpenedRightPage() {
        log.info("Checking that opened right page: CarsResultPage");
        Assert.assertTrue(isElementPresent(BASE_ELEMENT), "Invalid page open");
    }
}
