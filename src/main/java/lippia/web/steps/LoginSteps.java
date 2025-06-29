package lippia.web.steps;

import com.crowdar.core.PageSteps;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import junit.framework.Assert;
import lippia.web.services.MyAccountService;

public class LoginSteps extends PageSteps {


    @Given("El usuario se encuentra en la pagina de login de Clockify")
    public void home() {
        MyAccountService.navegarWeb();
    }

    @When("Hace clic en {string}")
    public void haceClicEn(String nombreMenu) {
        MyAccountService.clickMiCuenta();

    }

    @And("Hace clic en el boton {string} para continuar")
    public void haceClicEnElBotonParaContinuar(String nomBtn) {
        MyAccountService.clicBtn(nomBtn);
    }

    @Then("Se muestra el mensaje {string}")
    public void seMuestraElMensaje(String msjEsperado) {
        if (msjEsperado.equalsIgnoreCase("emailOK")) {
            boolean flag = MyAccountService.buscarNombreCorreo(msjEsperado);
            Assert.assertTrue("[WARNING] No se registro de forma correcta", flag);
        } else {
            boolean flag = MyAccountService.buscarMsjErrorLogin(msjEsperado);
            Assert.assertTrue("[WARNING] No se recupero el mensaje esperado: " + msjEsperado, flag);
        }
    }

    @And("Ingresa su correo {string} y contrasena {string}")
    public void ingresaSuCorreoYContrasena(String email, String password) {
        MyAccountService.ingresarEmailPass(email, password);
    }
}
