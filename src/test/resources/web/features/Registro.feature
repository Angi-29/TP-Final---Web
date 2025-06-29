@Registro

Feature: Registro

  Background:
    Given El usuario se encuentra en la pagina de Automation Practice Site

  @RegistroExitoso #@Smoke
  Scenario Outline: MY ACCOUNT - REGISTRATION "<titulo>"
    When Hace clic en el menu "Mi cuenta"
    And Ingresa las credenciales con correo "<email>" y contraseña "<password>"
    And Hace clic en el boton "REGISTER"
    Then El usuario visualiza el mensaje "<mensaje>"

    Examples:
      | titulo                                         | email                 | password       | mensaje                               |
      | Registration-Sign-in                           | emailOK               | 123456??ssS??? | Hello emailOK                         |
      | Registration with invalid Email-id             | emailInvalidSinArroba | 123456??ssS??? | invalid Email-id                      |
      | Registration with empty Email-id               |                       | 123456??ssS??? | Please provide a valid email address. |
      | Registration with empty password and  Email-id |                       |                | Please provide a valid email address. |


