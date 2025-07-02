@Registro

Feature: Registro

  Background:
    Given El usuario se encuentra en la pagina de Automation Practice Site
    #MY ACCOUNT - REGISTRATION TC 4-5
  @Registro @RegistroExitoso @RegistroFallido #@Smoke
  Scenario Outline: MY ACCOUNT - REGISTRATION "<titulo>"
    When Hace clic en el menu "Mi cuenta"
    And Ingresa las credenciales con correo "<email>" y contraseña "<password>"
    And Hace clic en el boton "REGISTER"
    Then El usuario visualiza el mensaje "<mensaje>"

    Examples:
      | titulo                                         | email                 | password       | mensaje                               |
      | Registration-Sign-in                           | emailOK               | 123456??ssS??? | Hello emailOK                         |
      | Registration with empty Email-password         | emailOK               |                | Please enter an account password.     |
      | Registration with empty password and  Email-id |                       |                | Please provide a valid email address. |


