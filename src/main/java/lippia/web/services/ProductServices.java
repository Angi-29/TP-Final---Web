package lippia.web.services;

import com.crowdar.core.actions.ActionManager;
import com.crowdar.core.actions.WebActionManager;
import lippia.web.constants.MyAccountConstants;
import lippia.web.constants.ProductContants;
import org.openqa.selenium.WebElement;

public class ProductServices extends ActionManager {

    public static String getTituloProducto() {
        return WebActionManager.waitVisibility(ProductContants.TITULO_NOMBRE_PRODUCTO).getText();
    }

    public static void agregaProductos(String cantProducto) {
        WebElement inputProducto = WebActionManager.waitVisibility(ProductContants.INPUT_CANTIDAD_PRODUCTO);
        inputProducto.clear();
        inputProducto.sendKeys(cantProducto);
        System.out.println("  ");
    }

    public static void clicBtn(String nomBtn) {
        switch (nomBtn) {
            case "Add to basket":
                WebActionManager.waitClickable(ProductContants.BTN_CLIC_ADD_TO_BASKET).click();
                break;
            case "View Basket":
                WebActionManager.waitClickable(MyAccountConstants.BTN_LOGIN).click();
                break;
            case "opcion3":
                System.out.println("Seleccionaste la opción 3");
                break;
            default:
                System.out.println("Opción no válida");
                break;
        }
    }

    public static boolean buscarMsjProductoAgregado(String msjBuscar) {
        try {
            String auxMsjBusar = String.format(ProductContants.MSJ_PRODUCTO_AGREGADO, msjBuscar);
            WebActionManager.waitVisibility(auxMsjBusar);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static String obtenerPrecioFinalDelProducto() {
        //Recuperamos el valor final del producto y le quitamos el simbolo ₹
        return WebActionManager.waitVisibility(ProductContants.PRESIO_FINAL_PRODUCTO).getText().replace("₹", "");
    }

    public static String ObtenerSubTotal() {
        return WebActionManager.waitVisibility(ProductContants.LBL_SUB_TOTAL).getText().replace("₹", "");
    }
}
