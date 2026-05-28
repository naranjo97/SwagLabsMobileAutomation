package com.swaglabs.tasks;

import com.swaglabs.actors.Actor;
import com.swaglabs.userinterfaces.LoginPage;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class IniciarSesion implements Task {

    private final String usuario;
    private final String contrasenia;

    public IniciarSesion(String usuario, String contrasenia) {
        this.usuario = usuario;
        this.contrasenia = contrasenia;
    }

    public static IniciarSesion conCredenciales(String usuario, String contrasenia) {
        return new IniciarSesion(usuario, contrasenia);
    }

    @Override
    public void performAs(Actor actor) {
        AndroidDriver driver = actor.getMobileAbility().getDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        System.out.println("-> " + actor.getName() + " está ingresando las credenciales...");

        WebElement userInput = wait.until(ExpectedConditions.visibilityOfElementLocated(LoginPage.USERNAME_INPUT));
        userInput.clear();
        userInput.sendKeys(usuario);

        WebElement passInput = wait.until(ExpectedConditions.visibilityOfElementLocated(LoginPage.PASSWORD_INPUT));
        passInput.clear();
        passInput.sendKeys(contrasenia);

        if (driver.isKeyboardShown()) {
            driver.hideKeyboard();
        }

        WebElement loginBtn = wait.until(ExpectedConditions.elementToBeClickable(LoginPage.LOGIN_BUTTON));
        loginBtn.click();

        System.out.println("-> Formulario de inicio de sesión enviado.");
    }
}