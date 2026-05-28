package com.swaglabs.userinterfaces;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;

public class CatalogPage {


    public static final By PRODUCT_ITEM = AppiumBy.xpath(
            "(//android.widget.ImageView[@content-desc='Product Image'])[1]"
    );

    public static final By ADD_TO_CART_BUTTON = AppiumBy.xpath(
            "//android.widget.Button[@resource-id='com.saucelabs.mydemoapp.android:id/cartBt']"
    );

    public static final By CART_ICON = AppiumBy.id(
            "com.saucelabs.mydemoapp.android:id/cartIV"
    );

    public static final By PROCEED_TO_CHECKOUT_BUTTON = AppiumBy.accessibilityId(
            "Confirms products for checkout"
    );
}