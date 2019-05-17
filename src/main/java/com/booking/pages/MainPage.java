package com.booking.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;

import static com.booking.utilities.PropertyManager.getProperty;

public class MainPage extends BasePageObject {

    private static final String BASE_URL = getProperty("main.page.url");
    private static final String GETAWAY_SALE_TOPIC = getProperty("main.page.banner1.topic");

    private By BASE_ELEMENT = By.id("logo_no_globe_new_logo");
    private By SEARCH_TBX = By.id("ss");
    private By LIST_SEARCH_RESULTS_LB = By.xpath("//ul[@role='listbox']/li");
    private By TOPIC = By.xpath("//span[@class='sb-searchbox__title-text']");
    private By CHECK_IN_CHECK_OUT_BTN = By.xpath("//div[@class='xp__dates-inner']");
    private By CURRENT_DATE_LB = By.xpath("//td[contains(@class, 'bui-calendar__date bui-calendar__date--today')]");
    private By GUESTS_TOGGLE = By.id("xp__guests__toggle");
    private String[] propsNames = {"group_adults", "group_children", "no_rooms"};
    private By SEARCH_BTN = By.xpath("//button[@data-sb-id='main']");
    private By GETAWAY_SALE_BUNNER = By.id("dealsCampaignGetaway2019");
    private By GETAWAY_SALE_BUNNER_TOPIC = By.xpath("//div[@id='dealsCampaignGetaway2019']" +
            "//h1[@class='bui-banner__title']");
    private By GETAWAY_SALE_BUNNER_BTN = By.xpath("//div[@id='dealsCampaignGetaway2019']//a");
    private By GETAWAY_SALE_BUNNER_CLOSE_BTN = By.id("btn_deals_index_banner_getaway2019_close");

    public MainPage(WebDriver driver, Logger log) {
        super(driver, log);
    }

    /**
     * Open MainPage with it's url
     */
    public void openPage() {
        log.info(String.format("Opening page: %s", BASE_URL));
        openUrl(BASE_URL);
        log.info("Main page opened!");
    }

    @Override
    public void isOpenedRightPage() {
        log.info("Checking that opened right page: MainPage");
        Assert.assertTrue(isElementPresent(BASE_ELEMENT), "Invalid page open");
    }

    private void search_suggested_destinations(String destination) {
        log.info("Choosing destination");
        type(destination, SEARCH_TBX);
        List<WebElement> searchResults = findAll(LIST_SEARCH_RESULTS_LB);
        for (WebElement searchResult : searchResults) {
            if (searchResult.getText().equals(destination)) {
                searchResult.click();
            }
        }
        click(TOPIC);
    }

    private void fill_travel_dates(int days) {
        log.info("Choosing number of days of stay");
        click(CHECK_IN_CHECK_OUT_BTN);
        click(CURRENT_DATE_LB);
        click(By.xpath(String.format("(//td[@class='bui-calendar__date'])[%d]", days - 1)));
        click(TOPIC);
    }

    private void fill_guests_toggle(int adults, int kids, int rooms) {
        log.info("Choosing numbers of persons and rooms");
        click(GUESTS_TOGGLE);
        int[] params = {adults, kids, rooms};
        int count;
        for (int i = 0; i < propsNames.length; i++) {
            By group = By.xpath(String.format("//*[@id='%s']/..//span[@class='bui-stepper__display']",
                    propsNames[i]));
            if (Integer.parseInt(find(group).getText()) > params[i]) {
                By subtract_btn = By.xpath(String.format("//*[@id='%s']/..//button[contains(@class,'subtract-button')]",
                        propsNames[i]));
                count = Math.abs(Integer.parseInt(find(group).getText()) - params[i]);
                while (count != 0) {
                    click(subtract_btn);
                    count--;
                }
            } else if (Integer.parseInt(find(group).getText()) < params[i]) {
                By subtract_btn = By.xpath(String.format("//*[@id='%s']/..//button[contains(@class,'add-button')]",
                        propsNames[i]));
                count = Math.abs(Integer.parseInt(find(group).getText()) - params[i]);
                while (count != 0) {
                    click(subtract_btn);
                    count--;
                }
            }
        }
        for (int i = 0; i < kids; i++) {
            By subtract_btn = By.xpath(String.format("//select[@data-group-child-age='%d']",
                    i));
            int random_age = set_iRandom_number(0, 17);
            selectDropdownByValue(subtract_btn, random_age);
        }
        click(TOPIC);
    }

    public ResultPage searchTravel(String city, int days, int adults, int kids, int rooms) {
        log.info(String.format("Try to find travel to '%s' in the next month for '%d' day(s) '%d' adult(s), " +
                "'%d' kid(s), '%d' room(s)", city, days, adults, kids, rooms));
        search_suggested_destinations(city);
        fill_travel_dates(days);
        fill_guests_toggle(adults, kids, rooms);
        click(SEARCH_BTN);
        return new ResultPage(driver, log);
    }

    public GetawaySalePage openGetawaySaleBanner() {
        log.info("Check that 'The Great Getaway Sale' banner on the main page and go to page");
        if (isElementPresent(GETAWAY_SALE_BUNNER)) {
            Assert.assertEquals(find(GETAWAY_SALE_BUNNER_TOPIC).getText(), GETAWAY_SALE_TOPIC,
                    "The wrong banner is displayed");
        } else {
            log.error("No needed bunner");
        }
        click(GETAWAY_SALE_BUNNER_BTN);
        return new GetawaySalePage(driver, log);
    }

    public void closeGetawaySaleBunner() {
        if (isElementPresent(GETAWAY_SALE_BUNNER)) {
            Assert.assertEquals(find(GETAWAY_SALE_BUNNER_TOPIC).getText(), GETAWAY_SALE_TOPIC,
                    "The wrong banner is displayed");
        } else {
            log.error("No needed bunner");
        }
        click(GETAWAY_SALE_BUNNER_CLOSE_BTN);
        if (isElementPresent(GETAWAY_SALE_BUNNER)) {
            log.error("Bunner not closed");
        }
    }
}
