package com.booking.search;

import com.booking.pages.MainPage;
import com.booking.pages.ResultPage;
import com.booking.utilities.CsvDataProviders;
import com.booking.utilities.TestUtilities;
import org.testng.annotations.Test;

import java.util.Map;

public class SearchFilterTest extends TestUtilities {

    @Test(priority = 1, dataProvider = "csvReader", dataProviderClass = CsvDataProviders.class)
    public void searchFilterTest(Map<String, String> testData) {
        // data
        String num = testData.get("num"), city = testData.get("city");
        int days = Integer.parseInt(testData.get("days")), adults = Integer.parseInt(testData.get("adults")),
        kids = Integer.parseInt(testData.get("kids")), rooms = Integer.parseInt(testData.get("rooms"));

        log.info(String.format("Starting searchFilterTest #%s ", num));

        // open main page
        MainPage mainPage = new MainPage(driver, log);
        mainPage.openPage();
        mainPage.isOpenedRightPage();

        // searching
        ResultPage resultPage = mainPage.searchTravel(city, days, adults, kids, rooms);
        resultPage.isOpenedRightPage();
        resultPage.checkFilters(city, adults, kids, rooms);
    }
}
