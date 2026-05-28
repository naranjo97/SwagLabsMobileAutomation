package com.swaglabs.tasks;

import com.swaglabs.actors.Actor;
import com.swaglabs.userinterfaces.CheckoutPage;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LlenarDireccion implements Task {

    private final String nombre;
    private final String direccion1;
    private final String direccion2;
    private final String ciudad;
    private final String estado;
    private final String zip;
    private final String pais;

    public LlenarDireccion(String nombre, String direccion1, String direccion2, String ciudad, String estado, String zip, String pais) {
        this.nombre = nombre;
        this.direccion1 = direccion1;
        this.direccion2 = direccion2;
        this.ciudad = ciudad;
        this.estado = estado;
        this.zip = zip;
        this.pais = pais;
    }

    public static LlenarDireccion conDatos(String nombre, String direccion1, String direccion2, String ciudad, String estado, String zip, String pais) {
        return new LlenarDireccion(nombre, direccion1, direccion2, ciudad, estado, zip, pais);
    }

    @Override
    public void performAs(Actor actor) {
        AndroidDriver driver = actor.getMobileAbility().getDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        System.out.println("-> " + actor.getName() + " está rellenando el formulario de envío...");


        wait.until(ExpectedConditions.visibilityOfElementLocated(CheckoutPage.FULL_NAME_INPUT)).sendKeys(nombre);
        driver.findElement(CheckoutPage.ADDRESS_LINE_1_INPUT).sendKeys(direccion1);
        driver.findElement(CheckoutPage.ADDRESS_LINE_2_INPUT).sendKeys(direccion2);
        driver.findElement(CheckoutPage.CITY_INPUT).sendKeys(ciudad);
        driver.findElement(CheckoutPage.STATE_INPUT).sendKeys(estado);
        driver.findElement(CheckoutPage.ZIP_CODE_INPUT).sendKeys(zip);

        WebElement countryField = driver.findElement(CheckoutPage.COUNTRY_INPUT);
        countryField.sendKeys(pais);


        if (driver.isKeyboardShown()) {
            driver.hideKeyboard();
        }

        System.out.println("-> Datos de envío completados. Presionando botón To Payment...");
        wait.until(ExpectedConditions.elementToBeClickable(CheckoutPage.TO_PAYMENT_BUTTON)).click();
    }
}
