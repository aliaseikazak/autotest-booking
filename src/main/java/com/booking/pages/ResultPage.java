package com.booking.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class ResultPage extends BasePageObject {

    private By BASE_ELEMENT = By.id("frm");
    private By DESTINATION = By.id("ss");
    private By ADULTS = By.id("group_adults");
    private By CHILDREN = By.id("group_children");
    private By ROOMS = By.id("no_rooms");
    private By BOOKING_LOGO = By.id("logo_no_globe_new_logo");

    ResultPage(WebDriver driver, Logger log) {
        super(driver, log);
    }

    @Override
    public void isOpenedRightPage() {
        log.info("Checking that opened right page: ResultPage");
        Assert.assertTrue(isElementPresent(BASE_ELEMENT), "Invalid page open");
    }

    public void checkFilters(String destination, int adults, int kids, int rooms) {
        log.info("Checking work of filters");
        Assert.assertEquals(find(DESTINATION).getAttribute("value"), destination,
                "Filter destination does not work");
        Assert.assertEquals(find(ADULTS).getAttribute("value"), Integer.toString(adults),
                "Filter adult does not work");
        Assert.assertEquals(find(CHILDREN).getAttribute("value"), Integer.toString(kids),
                "Filter children does not work");
        Assert.assertEquals(find(ROOMS).getAttribute("value"), Integer.toString(rooms),
                "Filter room does not work");
    }

    public MainPage goToMainPage() {
        find(BOOKING_LOGO).click();
        return new MainPage(driver, log);
    }
}
