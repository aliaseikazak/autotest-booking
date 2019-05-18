package com.booking.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.booking.utilities.PropertyManager.getProperty;

public class GetawaySalePage extends BasePageObject {

    private static final String GETAWAY_SALE_TOPIC = getProperty("main.page.banner1.topic");

    private By BASE_ELEMENT = By.xpath("//div[@class='deals-page-header-text']/h1");
    private String buiBox = "//div[@class='bui-box']";
    private By GETAWAY_DEAL = By.xpath(buiBox);
    private String getawayIcon = "(%s//*[@class='bui-badge bui-badge--callout'])[%d]";
    private String getawayPrice= "(%s//*[@class='deal-price-card'])[%d]";
    private List<Integer> prices = new ArrayList<Integer>();

    GetawaySalePage(WebDriver driver, Logger log) {
        super(driver, log);
    }

    @Override
    public void isOpenedRightPage() {
        log.info("Checking that opened right page: GetawaySalePage");
        Assert.assertEquals(find(BASE_ELEMENT).getText(), GETAWAY_SALE_TOPIC, "Invalid page open");
    }

    public ResultPage chooseTheCheapestOffer() {
        log.info("Check offers and find the cheapest");
        List<WebElement> getawayDeals = findAll(GETAWAY_DEAL);
        for (int i = 0; i < getawayDeals.size(); i++) {
            By GETAWAY_ICON = By.xpath(String.format(getawayIcon, buiBox, i + 1));
            if (!isElementPresent(GETAWAY_ICON)) {
                log.error("Not valid deal");
            }
            By GETAWAY_PRICE = By.xpath(String.format(getawayPrice, buiBox, i + 1));
            prices.add(Integer.parseInt(find(GETAWAY_PRICE).getText().replaceAll("[^0-9]","")));
        }
        By CHEAPEST_OFFER = By.xpath(String.format("(%s)[%d]", buiBox, prices.indexOf(Collections.min(prices)) + 1));
        find(CHEAPEST_OFFER).click();
        return new ResultPage(driver, log);
    }
}
