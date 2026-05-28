package com.swaglabs.userinterfaces;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;

public class LoginPage {

    public static final By USERNAME_INPUT = AppiumBy.id(
            "com.saucelabs.mydemoapp.android:id/nameET"
    );

    public static final By PASSWORD_INPUT = AppiumBy.id(
            "com.saucelabs.mydemoapp.android:id/passwordET"
    );

    public static final By LOGIN_BUTTON = AppiumBy.id(
            "com.saucelabs.mydemoapp.android:id/loginBtn"
    );
}