
Feature: Login

  Background:
    Given El usuario se encuentra en la pagina de Automation Practice Site


    #MY ACCOUNT - LOGIN TC 7-8
  @LoginExitoso @LoginFallido #@Smoke
  Scenario Outline: MY ACCOUNT - LOGIN - "<titulo>"
    When Hace clic en "Mi cuenta"
    And Ingresa su correo "<email>" y contrasena "<password>"
    And Hace clic en el boton "LOGIN" para continuar
    Then Se muestra el mensaje "<mensaje>"

    Examples:
      | titulo                                  | email             | password       | mensaje                                            |
      | Login Exitoso                           | emailOK@gmail.com | 123456??ssS??? | emailOK                                            |
      | Usuario Incorrecto                      | email_K@gmail.com | 123456??ssS??? | A user could not be found with this email address. |
      | No ingresa password                     | emailOK@gmail.com |                | Password is required.                              |
      | Usuario no ingresa cuenta ni contrasena |                   |                | Username is required.                              |
      | Usuario ingresa password incorrecto     | emailOK@gmail.com | 123456??sss??? | The password you entered for the username          |



  @LogOut #@Smoke
  Scenario: MY ACCOUNT - LOGIN - Login - Authentication
    Given Hace clic en "Mi cuenta"
    And Ingresa su correo "emailOK@gmail.com" y contrasena "123456??ssS???"
    And Hace clic en el boton "LOGIN" para continuar
    And Se muestra el mensaje "emailOK"
    When Hace clic en el boton "LogOut"
    Then usuario visualiza el titulo "Login"

