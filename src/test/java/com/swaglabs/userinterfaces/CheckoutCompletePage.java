package com.swaglabs.userinterfaces;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;

public class CheckoutCompletePage {

    public static final By PLACE_ORDER_BUTTON = AppiumBy.id("com.saucelabs.mydemoapp.android:id/paymentBtn");


    public static final By CHECKOUT_COMPLETE_TITLE = AppiumBy.id("com.saucelabs.mydemoapp.android:id/completeTV");
    public static final By CONTINUE_SHOPPING_BUTTON = AppiumBy.id("com.saucelabs.mydemoapp.android:id/shoopingBt");
}