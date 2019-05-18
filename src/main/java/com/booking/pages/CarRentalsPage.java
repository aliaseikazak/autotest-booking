package com.booking.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import static com.booking.utilities.PropertyManager.getProperty;

public class CarRentalsPage extends BasePageObject {

    private static final String CAR_RENTALS_TOPIC = getProperty("car-rentals.page.topic");

    private By BASE_ELEMENT = By.xpath("//*[@class='sb-searchbox__title-text']");
    private By PICKUP_LOCATION = By.id("ss_origin");
    private By LOCATION = By.xpath("//li[@data-label='Boston, Bolivia']");
    private By DRIVER_AGE_TBX = By.name("driversAgeInput");
    private By SEARCH_BTN = By.xpath("//button[@class='sb-searchbox__button  ']");

    CarRentalsPage(WebDriver driver, Logger log) {
        super(driver, log);
    }

    @Override
    public void isOpenedRightPage() {
        log.info("Checking that opened right page: CarRentalsPage");
        Assert.assertEquals(find(BASE_ELEMENT).getText(), CAR_RENTALS_TOPIC, "Invalid page open");
    }

    public CarsResultPage searchFlight(String destination, int age) {
        type(destination, PICKUP_LOCATION);
        waitForElementPresent(LOCATION);
        find(LOCATION).click();
        find(BASE_ELEMENT).click();
        type(Integer.toString(age), DRIVER_AGE_TBX);
        find(SEARCH_BTN).click();
        return new CarsResultPage(driver, log);
    }
}
