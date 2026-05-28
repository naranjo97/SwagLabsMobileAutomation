package com.swaglabs.tasks;

import com.swaglabs.actors.Actor;
import com.swaglabs.userinterfaces.CheckoutCompletePage;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class FinalizarOrden implements Task {

    public static FinalizarOrden enLaApp() {
        return new FinalizarOrden();
    }

    @Override
    public void performAs(Actor actor) {
        AndroidDriver driver = actor.getMobileAbility().getDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        System.out.println("-> " + actor.getName() + " está confirmando la revisión y presionando Place Order...");


        wait.until(ExpectedConditions.elementToBeClickable(CheckoutCompletePage.PLACE_ORDER_BUTTON)).click();
    }
}