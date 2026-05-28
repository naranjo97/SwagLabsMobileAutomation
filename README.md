# 📱 SwagLabs Mobile Automation - E2E Testing Framework

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Appium](https://img.shields.io/badge/Appium-660066?style=for-the-badge&logo=appium&logoColor=white)
![Cucumber](https://img.shields.io/badge/Cucumber-23D96C?style=for-the-badge&logo=cucumber&logoColor=white)
![Maven](https://img.shields.io/badge/Apache%20Maven-C71A36?style=for-the-badge&logo=Apache%20Maven&logoColor=white)

Este proyecto consiste en un framework de automatización de pruebas móviles **End-to-End (E2E)** robusto, desarrollado para la aplicación nativa *My Demo App* de Sauce Labs en Android. La solución está diseñada bajo exigentes estándares de calidad de software (SQA), aplicando principios de código limpio y desacoplamiento de datos.

---

## 🏗️ Arquitectura y Patrón de Diseño

El framework implementa el patrón de diseño **Screenplay**, el cual desplaza el enfoque tradicional de Page Object Model (POM) hacia una arquitectura orientada a actores, tareas e interacciones. Esto facilita el cumplimiento de los principios **SOLID**, garantizando que el código sea:

* **Altamente reutilizable:** Las tareas e interfaces están completamente separadas.
* **Mantenible:** Las modificaciones en la UI solo impactan las clases de interfaz de usuario (`userinterfaces`).
* **Legible:** El flujo de la prueba describe las acciones de manera natural y cercana al lenguaje humano.

---

## 📊 Estrategia de Datos: Data-Driven Testing

El núcleo del proyecto cuenta con una implementación de **Data-Driven Testing** mediante la integración de **Apache POI**.

* **Sin datos quemados (Hardcoded):** El script no almacena credenciales ni información de formularios en el código.
* **Mapeo Dinámico:** El componente utilitario `ExcelReader` extrae la información en tiempo de ejecución desde un archivo físico `.xlsx` (`src/test/resources/data/TestData.xlsx`).
* **Consumo Multipestaña:** En un único flujo, el framework es capaz de saltar dinámicamente entre pestañas específicas (`Login`, `Envio` y `Pago`) para completar el ciclo de compra.

---

## 🚀 Flujo Automatizado (User Journey)

El escenario implementado bajo **BDD (Behavior-Driven Development)** con **Cucumber** replica el siguiente comportamiento:

1.  **Given** El actor se encuentra en la pantalla de inicio de sesión.
2.  **When** Añade un producto disponible del catálogo al carrito (utilizando gestos nativos como `clickGesture`).
3.  **And** Realiza el inicio de sesión consumiendo datos dinámicos de usuario y contraseña desde Excel.
4.  **And** Completa el formulario de envío (`FullName`, `AddressLine1`, `City`, etc.) leyendo la pestaña "Envio".
5.  **And** Completa el formulario de pasarela de pagos leyendo la pestaña "Pago" y procesa la orden.
6.  **Then** Ejecuta un **Assert de JUnit** validando que la app muestre exitosamente la pantalla final de confirmación con el texto `"Checkout Complete"`.

---

## 🛠️ Requisitos e Instalación

Para replicar y ejecutar este proyecto de manera local, asegúrate de contar con los siguientes elementos configurados:

1.  **Java JDK 17** configurado en las variables de entorno.
2.  **Node.js** y **Appium 2.x** instalados globalmente.
3.  **UIAutomator2 Driver** activo en Appium.
4.  Un emulador Android (AVD) o dispositivo físico con la depuración USB activa.
5.  Servidor de Appium iniciado con permisos CORS si se inspecciona de forma externa:
    ```bash
    appium --allow-cors
    ```

---

## ✒️ Autor

* **Julio Cesar Naranjo** - *Aprendiz de Programación de Software - Etapa Práctica SQA* - [naranjo97](https://github.com/naranjo97)