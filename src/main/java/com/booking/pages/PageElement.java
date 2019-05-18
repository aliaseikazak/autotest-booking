package com.booking.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.booking.utilities.PropertyManager.getProperty;

public class PageElement {
    private static final int DEFAULT_TIMEOUT = 10;
    private static final String UNIQUE_ID = "//a[@class='xpb__link']/*[contains(text(), '%s')]";

    public enum PageElementEnum {
        FLIGHTS, CAR_RENTALS;
        private String ELEMENT;

        PageElementEnum() {
            this.ELEMENT = getProperty(this.toString());
        }

        private String getELEMENT() {
            return ELEMENT;
        }
    }

    static void clickElement(WebDriver driver, PageElementEnum selElement) {
        WebElement element = (new WebDriverWait(driver, DEFAULT_TIMEOUT)).until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath(String.format(UNIQUE_ID, selElement.getELEMENT()))));
        element.click();
    }
}
