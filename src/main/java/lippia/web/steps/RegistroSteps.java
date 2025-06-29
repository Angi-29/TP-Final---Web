package lippia.web.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import junit.framework.Assert;
import utils.Utils;
import lippia.web.services.MyAccountService;


public class RegistroSteps {
    private String email;

    @Given("El usuario se encuentra en la pagina de Automation Practice Site")
    public void elUsuarioSeEncuentraEnLaPaginaDeAutomationPracticeSite() {
        MyAccountService.navegarWeb();
    }

    @When("Hace clic en el menu {string}")
    public void haceClicEnElMenu(String nombreMenu) {
        MyAccountService.clickMenuCuenta();

    }

    @And("Ingresa las credenciales con correo {string} y contraseña {string}")
    public void ingresaLasCredencialesConCorreoYContraseña(String emailOpcion, String password) {
        email = Utils.correoAleatorio(emailOpcion);
        MyAccountService.enterCredentials(email, password);
    }

    @And("Hace clic en el boton {string}")
    public void haceClicEnElBotón(String nomBtn) {
        MyAccountService.clicBtn(nomBtn);
    }

    @Then("El usuario visualiza el mensaje {string}")
    public void elUsuarioVisualizaElMensaje(String msj) {
        if(msj.equalsIgnoreCase("Hello emailOK")){
            boolean flag = MyAccountService.buscarNombreCorreo(email);
            Assert.assertTrue("[WARNING] No se registro de forma correcta", flag);
        }

        if(msj.equalsIgnoreCase("Please provide a valid email address.")){
            boolean flag = MyAccountService.buscarErrorMsj();
            Assert.assertTrue("[WARNING] No es el resultado esperado", flag);
        }


        if(msj.equalsIgnoreCase("invalid Email-id")){
            boolean flag = MyAccountService.buscarHello();
            Assert.assertFalse("[WARNING] No es el resultado esperado", flag);
        }


    }
}
