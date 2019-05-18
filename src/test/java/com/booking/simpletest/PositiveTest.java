package com.booking.simpletest;

import com.booking.pages.MainPage;
import com.booking.utilities.TestUtilities;
import org.testng.annotations.Test;

public class PositiveTest extends TestUtilities {

    @Test
    public void positiveTest() {
        // open main page
        MainPage mainPage = new MainPage(driver, log);
        mainPage.openPage();
        mainPage.isOpenedRightPage();
    }
}
