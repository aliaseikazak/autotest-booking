package com.booking.carrental.search;

import com.booking.pages.CarRentalsPage;
import com.booking.pages.CarsResultPage;
import com.booking.pages.MainPage;
import com.booking.pages.PageElement;
import com.booking.utilities.TestUtilities;
import org.testng.annotations.Test;

public class SearchFilterCarRentalsTest extends TestUtilities {

    @Test(priority = 1)
    public void searchFilterCarRentalsTest() {
        // open main page
        MainPage mainPage = new MainPage(driver, log);
        mainPage.openPage();
        mainPage.isOpenedRightPage();

        CarRentalsPage carRentalsPage = mainPage.openCarRentalsPage(PageElement.PageElementEnum.CAR_RENTALS);
        carRentalsPage.isOpenedRightPage();

        CarsResultPage carsResultPage = carRentalsPage.searchFlight("Boston, Bolivia", 25);
        carsResultPage.isOpenedRightPage();
    }
}
