package lippia.web.services;

import com.crowdar.core.actions.WebActionManager;
import com.crowdar.driver.DriverManager;
import lippia.web.constants.HomePageConstants;
import lippia.web.pages.HomePage;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import utils.Select2Utils;



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
            // Scroll hasta el elemento
            JavascriptExecutor jse = (JavascriptExecutor) DriverManager.getDriverInstance();
            jse.executeScript("arguments[0].scrollIntoView(true);", elementoDeseado);

            // Click forzado con JS para evitar elementos solapados
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


    public static void enterCredentials(String firstName, String lastName, String email, String phone, String country, String address, String city, String state, String postCode) {

        WebActionManager.setInput(HomePageConstants.INPUT_FIRST_NAME, firstName);
        WebActionManager.setInput(HomePageConstants.INPUT_LAST_NAME, lastName);
        WebActionManager.setInput(HomePageConstants.INPUT_EMAIL, email);
        WebActionManager.setInput(HomePageConstants.INPUT_PHONE, phone);


        WebElement dropdown = WebActionManager.waitVisibility(HomePageConstants.SELECT_COUNTRY);

        ((JavascriptExecutor) DriverManager.getDriverInstance())
                .executeScript(
                        "var evt = new MouseEvent('mousedown', {bubbles: true}); arguments[0].dispatchEvent(evt);",
                        dropdown
                );
        if (dropdown == null) {
            throw new RuntimeException("El elemento del dropdown no se encontró.");
        }

       // Select2Utils.seleccionarOpcion(HomePageConstants.SELECT_COUNTRY, country);
       // String r = "/html/body/div[1]/div[2]/div/div/div/div/div[1]/form[3]/div[1]/div[1]/div/p[6]/select";
        //WebElement dropdownElement = WebActionManager.waitVisibility("xpath:"+r);
        // Crea un objeto Select utilizando el elemento dropdown
        //Select dropdown = new Select(dropdownElement);
        // Selecciona "Argentina" por su texto visible
        //dropdown.selectByVisibleText("Argentina");

        WebActionManager.setInput(HomePageConstants.INPUT_ADDRESS, address);
        WebActionManager.setInput(HomePageConstants.INPUT_CITY, city);

        Select2Utils.seleccionarOpcion(HomePageConstants.SELECT_STATE, state);

        WebActionManager.setInput(HomePageConstants.INPUT_POST_CODE, postCode);
    }

    private void seleccionarOpcionSelect2(String dropdownLocator, String opcionVisible) {
        WebElement dropdown = WebActionManager.getElement(dropdownLocator);
        ((JavascriptExecutor) DriverManager.getDriverInstance())
                .executeScript("var evt = new MouseEvent('mousedown', {bubbles: true}); arguments[0].dispatchEvent(evt);", dropdown);

        WebElement input = WebActionManager.getElement("css:.select2-input");
        input.sendKeys(opcionVisible);

        WebElement option = WebActionManager.getElement("xpath://div[contains(@class,'select2-result-label') and text()='" + opcionVisible + "']");
        option.click();
    }
}

