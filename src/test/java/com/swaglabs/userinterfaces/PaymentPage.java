package com.swaglabs.userinterfaces;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;

public class PaymentPage {
    public static final By FULL_NAME_INPUT = AppiumBy.id("com.saucelabs.mydemoapp.android:id/nameET");
    public static final By CARD_NUMBER_INPUT = AppiumBy.id("com.saucelabs.mydemoapp.android:id/cardNumberET");
    public static final By EXPIRATION_DATE_INPUT = AppiumBy.id("com.saucelabs.mydemoapp.android:id/expirationDateET");
    public static final By SECURITY_CODE_INPUT = AppiumBy.id("com.saucelabs.mydemoapp.android:id/securityCodeET");
    public static final By REVIEW_ORDER_BUTTON = AppiumBy.id("com.saucelabs.mydemoapp.android:id/paymentBtn");
}