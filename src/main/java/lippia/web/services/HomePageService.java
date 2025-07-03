package lippia.web.services;

import com.crowdar.core.Context;
import com.crowdar.core.actions.WebActionManager;
import com.crowdar.driver.DriverManager;
import lippia.web.constants.HomePageConstants;
import lippia.web.pages.HomePage;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;



import static com.crowdar.core.actions.ActionManager.getElement;
import static utils.Utils.scrollAndClickByJs;


public class HomePageService {

    public static void verificarCantidadDeArrivals(int cantidadEsperada) {
        int cantidadEncontrada = HomePage.getCantidadArrivals();

        if (cantidadEsperada != cantidadEncontrada) {
            throw new RuntimeException("La cantidad de artículos en Arrivals es incorrecta. Esperado: " +
                    cantidadEsperada + ", pero se encontró: " + cantidadEncontrada);
        }
    }

    public static void clicTituloProducto(String nomProducto) {
        String xpathProducto = HomePageConstants.getXpathProductoPorNombre(nomProducto);

        WebActionManager.waitClickable(xpathProducto);
        WebElement elementoDeseado = getElement(xpathProducto);

        if (elementoDeseado != null) {
            JavascriptExecutor jse = (JavascriptExecutor) DriverManager.getDriverInstance();
            jse.executeScript("arguments[0].scrollIntoView(true);", elementoDeseado);

            jse.executeScript("arguments[0].click();", elementoDeseado);
        } else {
            throw new RuntimeException("No se encontró el producto: " + nomProducto);
        }
    }

    public static void validarRedireccionAlProducto() {
        if (!HomePage.estaEnLaPaginaDelProducto()) {
            throw new AssertionError("No se redireccionó a la página del producto.");
        }
    }

    public static String getTituloProducto() {
        return WebActionManager.waitVisibility(HomePageConstants.TITULO_NOMBRE_PRODUCTO).getText();
    }

    public static void agregaProductos(String cantProducto) {
        WebElement inputProducto = WebActionManager.waitVisibility(HomePageConstants.INPUT_CANTIDAD_PRODUCTO);
        inputProducto.clear();
        inputProducto.sendKeys(cantProducto);
        System.out.println("  ");
    }

    public static void clicBtn(String nomBtn) {
        switch (nomBtn) {
            case "Add to basket":
                WebActionManager.waitClickable(HomePageConstants.BTN_CLIC_ADD_TO_BASKET).click();
                break;
            case "View Basket":
                WebActionManager.waitClickable(HomePageConstants.BTN_VIEW_BASKET).click();
                break;
            case "Proceed to Checkout":
                scrollAndClickByJs(HomePageConstants.BTN_CHECKOUT);
                break;
            default:
                System.out.println("Opción no válida");
                break;
        }

    }

    public static boolean buscarMsjProductoAgregado(String mensajeEsperado) {
        WebActionManager.waitVisibility(HomePageConstants.MENSAJE_CARRITO);
        String mensaje = WebActionManager.getText(HomePageConstants.MENSAJE_CARRITO);
        return mensaje.contains(mensajeEsperado) && mensaje.contains("has been added to your basket");
    }


    public static String obtenerTotalConImpuestos() {
        return WebActionManager.getText(HomePageConstants.TOTAL_CON_IMPUESTOS_LOCATOR);
    }


    public static void enterCredentials(String firstName, String lastName, String email, String phone, String country, String address, String city, String state, String postCode, String payMethod) {

        WebActionManager.setInput(HomePageConstants.INPUT_FIRST_NAME, firstName);
        WebActionManager.setInput(HomePageConstants.INPUT_LAST_NAME, lastName);
        WebActionManager.setInput(HomePageConstants.INPUT_EMAIL, email);
        WebActionManager.setInput(HomePageConstants.INPUT_PHONE, phone);


        WebActionManager.waitClickable(HomePageConstants.SELECT_COUNTRY).click();
        WebActionManager.waitVisibility(HomePageConstants.INPUT_SEARCH_COUNTRY).sendKeys(country);
        WebActionManager.waitVisibility(HomePageConstants.SELECT_MATCH_COUNTRY).click();

        WebActionManager.setInput(HomePageConstants.INPUT_ADDRESS, address);
        WebActionManager.setInput(HomePageConstants.INPUT_CITY, city);

        WebActionManager.waitClickable(HomePageConstants.SELECT_STATE_COUNTRY).click();
        WebActionManager.waitVisibility(HomePageConstants.INPUT_SEARCH_STATE_COUNTRY).sendKeys(state);
        WebActionManager.waitVisibility(HomePageConstants.SELECT_MATCH_STATE_COUNTRY).click();
        WebActionManager.setInput(HomePageConstants.INPUT_POST_CODE, postCode);


        WebActionManager.waitClickable(seleccionarMetodoDePago(payMethod)).click();

        Context.getInstance().setData("payMethod", payMethod);

        WebActionManager.waitClickable(HomePageConstants.BTN_PLACE_ORDER).click();


    }

    private static String seleccionarMetodoDePago(String paymentMethod) {
        switch (paymentMethod.trim()) {
            case "Direct Bank Transfer":
                return HomePageConstants.RBTN_Direct_Bank_Transfer;
            case "Check Payments":
                return HomePageConstants.RBTN_payment_method_cheque;

            case "Cash on Delivery":
                return HomePageConstants.RBTN_payment_method_cod;

            case "PayPal":
                return HomePageConstants.RBTN_payment_method_ppec_paypal;

            default:
                throw new IllegalArgumentException("Método de pago no reconocido: " + paymentMethod);
        }
    }

    public static String obtenerOderNumber() {
        return  WebActionManager.waitVisibility(HomePageConstants.LBL_ORDER_NUMBER).getText();
    }

    public static String obtenerOrderTotal() {
        return  WebActionManager.waitVisibility(HomePageConstants.LBL_ORDER_TOTAL).getText();
    }

    public static String obtenerOrderpayMethod() {
        return  WebActionManager.waitVisibility(HomePageConstants.LBL_ORDER_PAYMETHOD).getText();
    }
}

