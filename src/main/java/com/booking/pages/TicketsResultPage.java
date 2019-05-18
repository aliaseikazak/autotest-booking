package com.booking.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class TicketsResultPage extends BasePageObject {

    private By BASE_ELEMENT = By.xpath("//div[@class='keel-grid field-inline-grid']");

    TicketsResultPage(WebDriver driver, Logger log) {
        super(driver, log);
    }

    @Override
    public void isOpenedRightPage() {
        log.info("Checking that opened right page: TicketsResultPage");
        Assert.assertTrue(isElementPresent(BASE_ELEMENT), "Invalid page open");
    }
}
