package com.swaglabs.userinterfaces;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;

public class CheckoutPage {

    public static final By FULL_NAME_INPUT = AppiumBy.id("com.saucelabs.mydemoapp.android:id/fullNameET");
    public static final By ADDRESS_LINE_1_INPUT = AppiumBy.id("com.saucelabs.mydemoapp.android:id/address1ET");
    public static final By ADDRESS_LINE_2_INPUT = AppiumBy.id("com.saucelabs.mydemoapp.android:id/address2ET");
    public static final By CITY_INPUT = AppiumBy.id("com.saucelabs.mydemoapp.android:id/cityET");
    public static final By STATE_INPUT = AppiumBy.id("com.saucelabs.mydemoapp.android:id/stateET");
    public static final By ZIP_CODE_INPUT = AppiumBy.id("com.saucelabs.mydemoapp.android:id/zipET");
    public static final By COUNTRY_INPUT = AppiumBy.id("com.saucelabs.mydemoapp.android:id/countryET");

    public static final By TO_PAYMENT_BUTTON = AppiumBy.id("com.saucelabs.mydemoapp.android:id/paymentBtn");

    public static class CheckoutCompletePage {

        public static final By PLACE_ORDER_BUTTON = AppiumBy.id("com.saucelabs.mydemoapp.android:id/paymentBtn");


        public static final By CHECKOUT_COMPLETE_TITLE = AppiumBy.id("com.saucelabs.mydemoapp.android:id/completeTV");
        public static final By CONTINUE_SHOPPING_BUTTON = AppiumBy.id("com.saucelabs.mydemoapp.android:id/shoopingBt");
    }
}