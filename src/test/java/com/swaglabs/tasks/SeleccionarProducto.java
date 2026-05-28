package com.swaglabs.tasks;

import com.swaglabs.actors.Actor;
import com.swaglabs.userinterfaces.CatalogPage;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class SeleccionarProducto implements Task {

    public static SeleccionarProducto enElCatalogo() {
        return new SeleccionarProducto();
    }

    @Override
    public void performAs(Actor actor) {
        AndroidDriver driver = actor.getMobileAbility().getDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        System.out.println("-> Esperando visualización de la imagen del producto...");
        WebElement productImage = wait.until(ExpectedConditions.visibilityOfElementLocated(CatalogPage.PRODUCT_ITEM));

        System.out.println("-> Inyectando click nativo mediante comando clickGesture...");

        Map<String, Object> clickParams = new HashMap<>();
        clickParams.put("elementId", ((RemoteWebElement) productImage).getId());
        driver.executeScript("mobile: clickGesture", clickParams);

        System.out.println("-> Gesto enviado. Esperando cambio de pantalla...");

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("-> Buscando botón Add To Cart...");
        WebElement addToCart = wait.until(ExpectedConditions.elementToBeClickable(CatalogPage.ADD_TO_CART_BUTTON));
        addToCart.click();
        System.out.println("-> Botón Add To Cart presionado.");

        WebElement cartIcon = wait.until(ExpectedConditions.elementToBeClickable(CatalogPage.CART_ICON));
        cartIcon.click();
        System.out.println("-> Icono del carrito presionado.");

        WebElement proceedToCheckout = wait.until(ExpectedConditions.elementToBeClickable(CatalogPage.PROCEED_TO_CHECKOUT_BUTTON));
        proceedToCheckout.click();
        System.out.println("-> Botón Proceed To Checkout presionado con éxito.");
    }
}