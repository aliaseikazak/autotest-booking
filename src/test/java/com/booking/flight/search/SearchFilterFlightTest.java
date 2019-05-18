package com.booking.flight.search;

import com.booking.pages.FlightsPage;
import com.booking.pages.MainPage;
import com.booking.pages.PageElement;
import com.booking.pages.TicketsResultPage;
import com.booking.utilities.TestUtilities;
import org.testng.annotations.Test;

public class SearchFilterFlightTest extends TestUtilities {

    @Test(priority = 1)
    public void searchFilterFlightTest() {
        // open main page
        MainPage mainPage = new MainPage(driver, log);
        mainPage.openPage();
        mainPage.isOpenedRightPage();

        FlightsPage flightsPage = mainPage.openFlightsPage(PageElement.PageElementEnum.FLIGHTS);
        flightsPage.isOpenedRightPage();

        TicketsResultPage ticketsResultPage = flightsPage.searchFlight("Venice (TSF)", 5);
        ticketsResultPage.isOpenedRightPage();
    }
}
