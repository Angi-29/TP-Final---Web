package lippia.web.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import junit.framework.Assert;
import lippia.web.services.HomePageService;

import lippia.web.services.ProductServices;
import lippia.web.services.ShopService;

import com.crowdar.core.Context;


public class HomePageSteps {
    @Given("El usuario se encuentra en la pagina de la home")
    public void elUsuarioSeEncuentraEnLaPaginaDeShop() {
        ShopService.navegarWeb();
    }


    @When("verifica que existan {int} articulos en la sección de Arrivals")
    public void verificaQueExistanArticulosEnLaSecciónDeArrivals(int cantidadEsperada) {
        HomePageService.verificarCantidadDeArrivals(cantidadEsperada);
    }

    @And("hace clic en el producto {string} es redireccionado")
    public void elUsuarioHaceClicEnElProductoEsRedireccionado(String nomProducto) {
        HomePageService.clicTituloProducto(nomProducto);
        HomePageService.validarRedireccionAlProducto();
    }

    @And("esta en la pagina del producto {string} y agrega {string}")
    public void elUsuarioEstaEnLaPaginaDelProductoYAgrega(String nomProducto, String cantProducto) {
        boolean flag = HomePageService.getTituloProducto().equalsIgnoreCase(nomProducto);
        Assert.assertTrue("[WARNING] No se encuentra en la pagina del producto: " + nomProducto, flag);
        HomePageService.agregaProductos(cantProducto);
    }


    @Then("visualiza el mensaje: {string} has been added to your basket.")
    public void elUsuarioVisualizaElMensaje(String nomProducto) {
        boolean flag = HomePageService.buscarMsjProductoAgregado(nomProducto);
        Assert.assertTrue("[WARNING] No se encontró un mensaje que contenga: " + nomProducto, flag);

    }

    @And("hace clic en {string}")
    public void haceClicEn(String nomBtn) {
        HomePageService.clicBtn(nomBtn);
    }


    @And("valida que el subtotal es menor o igual al total con impuestos")
    public void validaQueElSubtotalEsMenorOIgualAlTotalConImpuestos() {
        double subtotal = Double.parseDouble(ProductServices.ObtenerSubTotal()
                .replace(",", "")
                .replace("₹", "")
                .trim());

        double total = Double.parseDouble(HomePageService.obtenerTotalConImpuestos()
                .replace(",", "")
                .replace("₹", "")
                .trim());

        Assert.assertTrue("[WARNING] El subtotal no es menor o igual al total. Subtotal: " + subtotal + ", Total: " + total,
                subtotal <= total);
        Context.getInstance().setData("subtotal", subtotal);
        Context.getInstance().setData("total", total);
    }


    @And("completa el formulario con sus datos {string},{string},{string},{string},{string},{string},{string},{string},{string},{string}")
    public void completaElFormularioConSusDatos(String firstName, String lastName, String email, String phone, String country, String address, String city, String state, String postCode, String payMethod) {
        HomePageService.enterCredentials(firstName, lastName, email, phone, country, address, city, state, postCode, payMethod);
    }

    @Then("visualiza los detalles de facturación, orden, adicionales y métodos de pago")
    public void visualizaLosDetallesDeFacturaciónOrdenAdicionalesYMétodosDePago() {
        String oderNumber = HomePageService.obtenerOderNumber();
        String OrderTotal = HomePageService.obtenerOrderTotal();
        String payMethod = HomePageService.obtenerOrderpayMethod();

        Assert.assertNotNull("[WARNING] No se recupero el Numero de Orden", oderNumber);
        String auxMetodoPagoSelecionado = (String) Context.getInstance().getData("payMethod");
        Assert.assertEquals("[WARNING] No es el metodo de pago seleccionado " + auxMetodoPagoSelecionado, auxMetodoPagoSelecionado, payMethod);


    }
}