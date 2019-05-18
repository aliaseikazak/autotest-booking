package com.booking.accommodation.banner;

import com.booking.pages.GetawaySalePage;
import com.booking.pages.MainPage;
import com.booking.pages.ResultPage;
import com.booking.utilities.TestUtilities;
import org.testng.annotations.Test;

public class GetawaySaleTest extends TestUtilities {

    @Test(priority = 1)
    public void getawaySaleTest() {
        // open main page
        MainPage mainPage = new MainPage(driver, log);
        mainPage.openPage();
        mainPage.isOpenedRightPage();

        GetawaySalePage getawaySalePage = mainPage.openGetawaySaleBanner();
        getawaySalePage.isOpenedRightPage();

        ResultPage resultPage = getawaySalePage.chooseTheCheapestOffer();
        resultPage.isOpenedRightPage();

        mainPage = resultPage.goToMainPage();
        mainPage.isOpenedRightPage();
        mainPage.closeGetawaySaleBunner();

    }
}
