package com.swaglabs.tasks;

import com.swaglabs.actors.Actor;
import com.swaglabs.userinterfaces.PaymentPage;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LlenarPago implements Task {

    private final String nombre;
    private final String numeroTarjeta;
    private final String fechaExpiracion;
    private final String codigoSeguridad;

    public LlenarPago(String nombre, String numeroTarjeta, String fechaExpiracion, String codigoSeguridad) {
        this.nombre = nombre;
        this.numeroTarjeta = numeroTarjeta;
        this.fechaExpiracion = fechaExpiracion;
        this.codigoSeguridad = codigoSeguridad;
    }

    public static LlenarPago conDatos(String nombre, String numeroTarjeta, String fechaExpiracion, String codigoSeguridad) {
        return new LlenarPago(nombre, numeroTarjeta, fechaExpiracion, codigoSeguridad);
    }

    @Override
    public void performAs(Actor actor) {
        AndroidDriver driver = actor.getMobileAbility().getDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        System.out.println("-> " + actor.getName() + " está rellenando el formulario de pago...");

        wait.until(ExpectedConditions.visibilityOfElementLocated(PaymentPage.FULL_NAME_INPUT)).sendKeys(nombre);
        driver.findElement(PaymentPage.CARD_NUMBER_INPUT).sendKeys(numeroTarjeta);
        driver.findElement(PaymentPage.EXPIRATION_DATE_INPUT).sendKeys(fechaExpiracion);
        driver.findElement(PaymentPage.SECURITY_CODE_INPUT).sendKeys(codigoSeguridad);

        if (driver.isKeyboardShown()) {
            driver.hideKeyboard();
        }

        System.out.println("-> Datos de pago completados. Presionando Review Order...");
        wait.until(ExpectedConditions.elementToBeClickable(PaymentPage.REVIEW_ORDER_BUTTON)).click();
    }
}
