package lippia.web.steps;

import com.crowdar.core.PageSteps;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import junit.framework.Assert;
import lippia.web.services.ProductServices;
import lippia.web.services.ShopService;

public class CarritoSteps extends PageSteps {


    @Given("El usuario se encuentra en la pagina de shop")
    public void elUsuarioSeEncuentraEnLaPaginaDeShop() {
        ShopService.navegarWeb();
    }

    @When("el usuario hace clic en el producto {string} es redireccionado")
    public void elUsuarioHaceClicEnElProductoEsRedireccionado(String nomProducto) {
        ShopService.clicTituloProducto(nomProducto);
    }

    @And("el usuario esta en la pagina del producto {string} y agrega {string}")
    public void elUsuarioEstaEnLaPaginaDelProductoYAgrega(String nomProducto, String cantProducto) {
        boolean flag = ProductServices.getTituloProducto().equalsIgnoreCase(nomProducto);
        Assert.assertTrue("[WARNING] No se encuentra en la pagina del producto: " + nomProducto, flag);
        ProductServices.agregaProductos(cantProducto);
    }

    @And("hace clic {string}")
    public void haceClic(String nomBtn) {
        ProductServices.clicBtn(nomBtn);
    }


    @Then("el usuario visuala mensaje: {string} × “{string}” have been added to your basket.")
    public void elUsuarioVisualaMensajeHaveBeenAddedToYourBasket(String cantProducto, String nomProducto) {
        String msjBuscar = cantProducto + " × “" + nomProducto + "” have been added to your basket.";
        boolean flag = ProductServices.buscarMsjProductoAgregado(msjBuscar);
        Assert.assertTrue("[WARNING] No se encontro el mensaje  " + msjBuscar, flag);

    }

    @And("el usuario visualiza el precio correcto del calculo entre {string} por el valor final")
    public void elUsuarioVisualizaElPrecioCorrectoDelCalculoEntrePorElValorFinal(String cantProducto) {
        double precioFinal = Double.parseDouble(ProductServices.obtenerPrecioFinalDelProducto());
        Double calculoSubTotal = Double.parseDouble(cantProducto) * precioFinal;
        double totalObtenido = Double.parseDouble(ProductServices.ObtenerSubTotal().replace(",", ""));
        Assert.assertEquals("[WARNING] No es el valor esperado: " + calculoSubTotal, calculoSubTotal, totalObtenido);
    }

    @When("el usuario ordena los productos por {string}")
    public void elUsuarioOrdenaLosProductosPor(String ordenarProducto) {
        ShopService.ordenarProducto(ordenarProducto);
    }

    @Then("el usuario visualiza el prodcuto {string} como primero en la lista")
    public void elUsuarioVisualizaElProdcutoComoPrimeroEnLaLista(String nomProducto) {
        String primerElementoDeLista = ShopService.obtenerPrimerElementoDeListaDeProducto();
        boolean flag = primerElementoDeLista.equalsIgnoreCase(nomProducto);
        Assert.assertTrue("[WARNING] No coinciden productos como primero", flag);
    }
}
