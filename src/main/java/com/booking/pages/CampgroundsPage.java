package com.booking.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import static com.booking.utilities.PropertyManager.getProperty;

public class CampgroundsPage extends BasePageObject {

    private static final String CAMPGROUNDS_TOPIC = getProperty("campgrounds.page.topic");

    private By BASE_ELEMENT = By.xpath("//*[@class='sb-searchbox__title-text']");

    CampgroundsPage(WebDriver driver, Logger log) {
        super(driver, log);
    }

    @Override
    public void isOpenedRightPage() {
        log.info("Checking that opened right page: CampgroundsPage");
        Assert.assertEquals(find(BASE_ELEMENT).getText(), CAMPGROUNDS_TOPIC, "Invalid page open");
    }

    public MainPage closePageAndSwithToMainPage() {
        switchBetweenTabs(true, 0);
        return new MainPage(driver, log);
    }
}
