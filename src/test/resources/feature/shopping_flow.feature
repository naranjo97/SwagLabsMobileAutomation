Feature: Flujo de compra en la aplicación móvil

  @FlujoCompra
  Scenario: Compra exitosa de un producto desde el catálogo utilizando datos externos
    Given que el actor se encuentra en la pantalla de inicio de sesión
    When añade un producto disponible al carrito de compras
    And realiza el login con las credenciales del archivo externo
    Then debería ver que el producto en el carrito corresponde al seleccionado
    When realiza el proceso de checkout con la información de envío del archivo externo
    Then debería validar que la orden se ha realizado correctamente