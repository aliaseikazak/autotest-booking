package com.booking.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import static com.booking.utilities.PropertyManager.getProperty;

public class FlightsPage extends BasePageObject {

    private static final String FLIGHTS_TOPIC = getProperty("flights.page.topic");

    private By BASE_ELEMENT = By.xpath("//div[@class='keel-container s-t-bp']");
    private By ONE_WAY_BTN = By.xpath("//label[@class='r9-radiobuttonset-label'][@title='One-way']");
    private By TO_TBX = By.xpath("//input[@name='destination'][@placeholder='To where?']");
    private By DESTINATION = By.xpath("//*[@data-short-name='Venice (TSF)']");
    private By DEPARTURE = By.xpath("//div[@aria-label='Departure date input'][@class='dateInput size-l input-flat']");
    private By SEARCH_BTN =By.xpath("//div[@class='fieldBlock buttonBlock']//button[@class='Common-Widgets-" +
            "Button-ButtonDeprecated Common-Widgets-Button-Button Button-Gradient size-l searchButton']");

    FlightsPage(WebDriver driver, Logger log) {
        super(driver, log);
    }

    @Override
    public void isOpenedRightPage() {
        log.info("Checking that opened right page: FlightsPage");
        Assert.assertEquals(find(BASE_ELEMENT).getText(), FLIGHTS_TOPIC, "Invalid page open");
    }

    public TicketsResultPage searchFlight(String destination, int days) {
        find(ONE_WAY_BTN).click();
        type(destination, TO_TBX);
        waitForElementPresent(DESTINATION);
        find(DESTINATION).click();
        find(BASE_ELEMENT).click();
        find(DEPARTURE).click();
        click(By.xpath(String.format("(//div[@class='col-day '])[%d]", days - 1)));
        find(BASE_ELEMENT).click();
        find(SEARCH_BTN).click();
        return new TicketsResultPage(driver, log);
    }
}
