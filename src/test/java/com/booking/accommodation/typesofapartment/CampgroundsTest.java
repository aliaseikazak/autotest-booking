package com.booking.accommodation.typesofapartment;

import com.booking.pages.CampgroundsPage;
import com.booking.pages.MainPage;
import com.booking.utilities.TestUtilities;
import org.testng.annotations.Test;

public class CampgroundsTest extends TestUtilities {

    @Test
    public void positiveTest() {
        // open main page
        MainPage mainPage = new MainPage(driver, log);
        mainPage.openPage();
        mainPage.isOpenedRightPage();

        CampgroundsPage campgroundsPage = mainPage.chooseCampgrounds();
        campgroundsPage.isOpenedRightPage();

        mainPage = campgroundsPage.closePageAndSwithToMainPage();
        mainPage.isOpenedRightPage();
    }
}
