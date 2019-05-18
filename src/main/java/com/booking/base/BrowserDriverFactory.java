package com.booking.base;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.phantomjs.PhantomJSDriver;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static com.booking.utilities.PropertyManager.getSettingsProperty;
import static org.openqa.selenium.remote.BrowserType.*;

public class BrowserDriverFactory {

    private static final String BROWSER = getSettingsProperty("browser");
    private static String getIncognitoCH = getSettingsProperty("incognitoCH");
    private static String getSafeBrowsing = getSettingsProperty("safeBrowsing");
    private static String getDisablePopup = getSettingsProperty("disablePopup");
    private static String getInsureContent = getSettingsProperty("insecureContent");
    private static String getDownloadDefaultDirectoryCH = getSettingsProperty("downloadDefaultDirectoryCH");
    private static String getDownloadDirectoryUpgrade = getSettingsProperty("downloadDirectoryUpgrade");
    private static String getMaximizedWindowCH = getSettingsProperty("maximizedWindowCH");
    private static String getTrue = getSettingsProperty("true");
    private static String getDownloadFolder = getSettingsProperty("downloadFolder");
    private static final String NAME_EXP_OPT = "prefs";
    private static String getLocaleFuncCH = getSettingsProperty("funcLocale");
    private static String getLocaleName = getSettingsProperty("locale");
    private static String getIncognitoFF = getSettingsProperty("incognitoFF");
    private static String getSaveAllFF = getSettingsProperty("saveAllFF");
    private static String getDownloadDefaultDirectoryFF = getSettingsProperty("downloadDefaultDirectoryFF");
    private static String getAgreeToChangePath = getSettingsProperty("agreeToChangePath");
    private static int getChangePathLoad = Integer.parseInt(getSettingsProperty("changePathLoad"));
    private static String getTypeOfFiles = getSettingsProperty("typeOfFiles");
    private static final String getGetLocaleFuncFF = "intl.accept_languages";

    private ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
    private String browser;
    private Logger log;

    public BrowserDriverFactory(String browser, Logger log) {
        if (browser != null) {
            this.browser = browser.toLowerCase();
        } else {
            this.browser = BROWSER.toLowerCase();
        }
        this.log = log;
    }

    WebDriver createDriver() {
        log.info(String.format("SET_UP: Creating driver: '%s'", browser));
        switch (BROWSER) {
            case CHROME:
                driver.set(new ChromeDriver(setChromeProfile()));
                break;
            case "chrome-headless":
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--headless");
                driver.set(new ChromeDriver(chromeOptions));
                break;
            case FIREFOX:
                driver.set(new FirefoxDriver(setFireFoxProfile()));
                break;
            case "firefox-headless":
                FirefoxBinary firefoxBinary = new FirefoxBinary();
                firefoxBinary.addCommandLineOptions("--headless");
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.setBinary(firefoxBinary);
                driver.set(new FirefoxDriver(firefoxOptions));
                break;
            case PHANTOMJS:
                driver.set(new PhantomJSDriver());
                break;
            default:
                log.info(String.format("Do not know how to start: '%s' driver, starting chrome.", browser));
                driver.set(new ChromeDriver(setChromeProfile()));
                break;
        }
        return driver.get();
    }

    private static ChromeOptions setChromeProfile() {
        HashMap<String, Object> chromePrefs = new HashMap<>();
        chromePrefs.put(getSafeBrowsing, getTrue);
        chromePrefs.put(getDownloadDefaultDirectoryCH, getDownloadFolder);
        chromePrefs.put(getDownloadDirectoryUpgrade, getTrue);
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments(Arrays.asList(getDisablePopup, getInsureContent, getIncognitoCH,
                getMaximizedWindowCH, getLocaleFuncCH+getLocaleName));
        chromeOptions.setExperimentalOption(NAME_EXP_OPT, chromePrefs);
        return chromeOptions;
    }

    private static FirefoxOptions setFireFoxProfile() {
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.addPreference(getSaveAllFF, getTypeOfFiles)
                .addPreference(getAgreeToChangePath, getChangePathLoad)
                .addPreference(getDownloadDefaultDirectoryFF, getDownloadFolder)
                .addPreference(getGetLocaleFuncFF, getLocaleName);
        firefoxOptions.addArguments(getIncognitoFF);
        return firefoxOptions;
    }

    public WebDriver createChromeWithMobileEmulation(String deviceName) {
        log.info(String.format("Starting driver with %s emulation]", deviceName));
        Map<String, String> mobileEmulation = new HashMap<>();
        mobileEmulation.put("deviceName", deviceName);
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setExperimentalOption("mobileEmulation", mobileEmulation);
        driver.set(new ChromeDriver(chromeOptions));
        return driver.get();
    }
}
