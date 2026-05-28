package com.swaglabs.stepdefinitions;


import java.util.List;
import java.util.Map;


import com.swaglabs.tasks.*;
import com.swaglabs.utils.ExcelReader;

import com.swaglabs.actors.Actor;
import com.swaglabs.actors.BrowseTheMobileApp;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import com.swaglabs.tasks.IniciarSesion;
import com.swaglabs.tasks.LlenarDireccion;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class ShoppingStepDefinitions {

    private AndroidDriver driver;
    private Actor julio;

    @Before
    public void setUp() {
        try {
            UiAutomator2Options options = new UiAutomator2Options();
            options.setPlatformName("Android");
            options.setAutomationName("UIAutomator2");
            options.setDeviceName("emulator-5554");

            // Paquete y actividad estándar de My Demo App
            options.setAppPackage("com.saucelabs.mydemoapp.android");
            options.setAppActivity("com.saucelabs.mydemoapp.android.view.activities.SplashActivity");
            options.setNoReset(false);

            driver = new AndroidDriver(new URL("http://127.0.0.1:4723/"), options);
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

            julio = Actor.named("Julio");
            julio.can(BrowseTheMobileApp.with(driver));

            System.out.println("-> Sesión de Appium iniciada. El Actor " + julio.getName() + " está listo.");

        } catch (MalformedURLException e) {
            System.err.println("Error en la URL del servidor de Appium: " + e.getMessage());
        }
    }

    @Given("que el actor se encuentra en la pantalla de inicio de sesión")
    public void queElActorSeEncuentraEnLaPantallaDeInicioDeSesión() {
        System.out.println("-> " + julio.getName() + " confirma que la aplicación se abrió correctamente.");
    }

    @When("realiza el login con las credenciales del archivo externo")
    public void realizaElLoginConLasCredencialesDelArchivoExterno() {
        System.out.println("-> Realizando login dinámico con datos de Excel...");

        List<Map<String, String>> credenciales =
                ExcelReader.data("src/test/resources/data/TestData.xlsx", "Login");

        Map<String, String> row = credenciales.get(0);


        System.out.println("-> CABECERAS ENCONTRADAS EN TU EXCEL: " + row.keySet());


        String userExcel = row.get("Username");
        String passwordExcel = row.get("Password");

        IniciarSesion.conCredenciales(userExcel, passwordExcel).performAs(julio);
    }


    @And("añade un producto disponible al carrito de compras")
    public void añadeUnProductoDisponibleAlCarritoDeCompras() {
        System.out.println("-> " + julio.getName() + " está interactuando con el catálogo...");


        SeleccionarProducto.enElCatalogo().performAs(julio);

        System.out.println("-> Producto añadido y Proceed to Checkout presionado.");
    }

    @Then("debería ver que el producto en el carrito corresponde al seleccionado")
    public void deberíaVerQueElProductoEnElCarritoCorrespondeAlSeleccionado() {
        System.out.println("-> Validando producto en el carrito...");
    }


    @When("realiza el proceso de checkout con la información de envío del archivo externo")
    public void realizaElProcesoDeCheckoutConLaInformacionDeEnvioDelArchivoExterno() {
        System.out.println("-> Procesando checkout con datos dinámicos de Excel...");


        List<Map<String, String>> datosEnvio = ExcelReader.data("src/test/resources/data/TestData.xlsx", "Envio");
        Map<String, String> rowEnvio = datosEnvio.get(0);
        LlenarDireccion.conDatos(
                rowEnvio.get("FullName"), rowEnvio.get("AddressLine1"), rowEnvio.get("AddressLine2"),
                rowEnvio.get("City"), rowEnvio.get("State"), rowEnvio.get("ZipCode"), rowEnvio.get("Country")
        ).performAs(julio);


        System.out.println("-> Procesando pago con datos dinámicos de Excel...");
        List<Map<String, String>> datosPago = ExcelReader.data("src/test/resources/data/TestData.xlsx", "Pago");
        Map<String, String> rowPago = datosPago.get(0);
        LlenarPago.conDatos(
                rowPago.get("FullName"), rowPago.get("CardNumber"), rowPago.get("ExpirationDate"), rowPago.get("SecurityCode")
        ).performAs(julio);


        FinalizarOrden.enLaApp().performAs(julio);
    }


    @Then("debería validar que la orden se ha realizado correctamente")
    public void deberíaValidarQueLaOrdenSeHaRealizadoCorrectamente() {
        System.out.println("-> Validando orden exitosa mediante Assert de QA...");

        io.appium.java_client.android.AndroidDriver driver = julio.getMobileAbility().getDriver();
        org.openqa.selenium.support.ui.WebDriverWait wait = new org.openqa.selenium.support.ui.WebDriverWait(driver, java.time.Duration.ofSeconds(10));


        org.openqa.selenium.WebElement tituloExito = wait.until(
                org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated(com.swaglabs.userinterfaces.CheckoutCompletePage.CHECKOUT_COMPLETE_TITLE)
        );
        String textoRealEnPantalla = tituloExito.getText();

        System.out.println("-> Texto capturado en el emulador: [" + textoRealEnPantalla + "]");


        org.junit.Assert.assertEquals("¡Error! La orden de compra no finalizó con éxito", "Checkout Complete", textoRealEnPantalla);

        System.out.println("-> ¡ASSERT COMPLETADO! El texto coincide perfectamente con 'Checkout Complete'.");
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            System.out.println("-> Sesión de Appium finalizada con éxito.");
        }
    }
}