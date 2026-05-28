package com.swaglabs.actors;

import io.appium.java_client.android.AndroidDriver;

public class BrowseTheMobileApp {
    private final AndroidDriver driver;

    public BrowseTheMobileApp(AndroidDriver driver) {
        this.driver = driver;
    }

    public static BrowseTheMobileApp with(AndroidDriver driver) {
        return new BrowseTheMobileApp(driver);
    }

    public AndroidDriver getDriver() {
        return driver;
    }
}