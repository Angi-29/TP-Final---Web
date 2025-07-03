Feature: Flujo de compra

  Background:
    Given El usuario se encuentra en la pagina de la home

  @E2E @Compra @Smoke
    #Home Page - TC:17/18. Home-Arrivals-Add to Basket-Items-Check-out-Payment Gateway
  Scenario Outline: Comprar un libro desde la home
    When verifica que existan 3 articulos en la sección de Arrivals
    And hace clic en el producto "<nomProducto>" es redireccionado
    And esta en la pagina del producto "<nomProducto>" y agrega "<cantProducto>"
    And hace clic en "Add to basket"
    Then visualiza el mensaje: "Selenium Ruby" has been added to your basket.
    And hace clic en "View Basket"
    And valida que el subtotal es menor o igual al total con impuestos
    And hace clic en "Proceed to Checkout"
    And completa el formulario con sus datos "<firstName>","<lastName>","<email>","<phone>","<country>","<address>","<city>","<state>","<postCode>","<payMethod>"
    Then visualiza los detalles de facturación, orden, adicionales y métodos de pago
    Examples:
      | nomProducto   | cantProducto | firstName | lastName | email              | phone  | country   | address   | city    | state   | postCode | payMethod             |
      | Selenium Ruby | 1            | Juan      | Perez    | juanperez@test.com | 123456 | Argentina | calle 123 | Cordoba | Cordoba | 12345    | Direct Bank Transfer |