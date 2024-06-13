package com.seleniumFramework.utilities;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BrowserUtils {

    public static String getChromeBrowserVersion() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        WebDriver driver = new ChromeDriver(options);
        return getBrowserVersion(driver, "Chrome/");
    }

    public static String getEdgeBrowserVersion() {
        WebDriverManager.edgedriver().setup();
        EdgeOptions options = new EdgeOptions();
        WebDriver driver = new EdgeDriver(options);
        return getBrowserVersion(driver, "Edg/");
    }

    public static String getFirefoxBrowserVersion() {
        WebDriverManager.firefoxdriver().setup();
        FirefoxOptions options = new FirefoxOptions();
        WebDriver driver = new FirefoxDriver(options);
        return getBrowserVersion(driver, "Firefox/");
    }

//    public static String getOperaBrowserVersion() {
//        WebDriverManager.operadriver().setup();
//        OperaOptions options = new OperaOptions();
//        WebDriver driver = new OperaDriver(options);
//        return getBrowserVersion(driver, "OPR/");
//    }

    private static String getBrowserVersion(WebDriver driver, String browserIdentifier) {
        String version = "";
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            version = (String) js.executeScript("return navigator.userAgent;");
            if (version.contains(browserIdentifier)) {
                version = version.substring(version.indexOf(browserIdentifier) + browserIdentifier.length());
                version = version.split(" ")[0];
            }
        } finally {
            driver.quit();
        }
        return version;
    }

    public static void main(String[] args) {
        System.out.println("Chrome Version: " + getChromeBrowserVersion());
        System.out.println("Edge Version: " + getEdgeBrowserVersion());
        System.out.println("Firefox Version: " + getFirefoxBrowserVersion());
        //System.out.println("Opera Version: " + getOperaBrowserVersion());
    }
}