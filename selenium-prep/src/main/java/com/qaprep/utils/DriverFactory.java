package com.qaprep.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverFactory {

public static WebDriver getDriver() {

    return new ChromeDriver();
}

}
