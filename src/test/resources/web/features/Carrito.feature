Feature: Login

  Background:
    Given El usuario se encuentra en la pagina de shop

  @AgregarProductoCarrito #@Smoke
  Scenario Outline: El usuario agrega "<cantProducto>" producto de "<nomProducto>"
    When el usuario hace clic en el producto "<nomProducto>" es redireccionado
    And el usuario esta en la pagina del producto "<nomProducto>" y agrega "<cantProducto>"
    And hace clic "Add to basket"
    Then el usuario visuala mensaje: "<cantProducto>" × “"<nomProducto>"” have been added to your basket.
    And el usuario visualiza el precio correcto del calculo entre "<cantProducto>" por el valor final
    Examples:
      | nomProducto                  | cantProducto |
      | HTML5 Forms                  | 3            |
      | Functional Programming in JS | 10           |

  @OrdenarProducto #@Smoke
  Scenario Outline: El usuario ordena los productos por "<ordenarProducto>"
    When el usuario ordena los productos por "<ordenarProducto>"
    Then el usuario visualiza el prodcuto "<nomProducto>" como primero en la lista

    Examples:
      | ordenarProducto            | nomProducto               |
      | Sort by popularity         | Android Quick Start Guide |
      | Sort by price: high to low | Selenium Ruby             |