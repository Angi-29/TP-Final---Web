package lippia.web.services;

import com.crowdar.core.PropertyManager;
import com.crowdar.core.actions.ActionManager;
import com.crowdar.core.actions.WebActionManager;
import com.crowdar.driver.DriverManager;
import lippia.web.constants.ShopConstants;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

import static com.crowdar.core.actions.WebActionManager.navigateTo;

public class ShopService extends ActionManager {
    public static void navegarWeb() {
        navigateTo(PropertyManager.getProperty("web.base.url.shop"));
    }

    public static void clicTituloProducto(String nomProducto) {
        List<WebElement> elementos = WebActionManager.getElements(ShopConstants.LISTA_LINK_PRODUCTO);

        WebElement elementoDeseado = null;
        for (WebElement elemento : elementos) {
            WebElement h3 = elemento.findElement(By.tagName("h3"));
            if (h3.getText().equals(nomProducto)) {
                elementoDeseado = elemento;
                break;
            }

        }

        if (elementoDeseado != null) {
            JavascriptExecutor jse = DriverManager.getDriverInstance();
            jse.executeScript("arguments[0].scrollIntoView()", new Object[]{elementoDeseado});
            elementoDeseado.click();

        } else {
            System.out.println("No se encontró ningún elemento con el texto 'Android Quick Start Guide'");
        }
    }

    public static void ordenarProducto(String ordenarProducto) {

        WebElement selectElemento = WebActionManager.waitVisibility(ShopConstants.SELECT_ORDER_ELEMENT);
        Select select = new Select(selectElemento);
        select.selectByVisibleText(ordenarProducto);

    }

    public static String obtenerPrimerElementoDeListaDeProducto() {
        WebElement titulo = WebActionManager.waitVisibility(ShopConstants.LBL_PRIMER_ELEMENTO_LISTA).findElement(By.tagName("h3"));
        return titulo.getText();
    }

    public static void navegarWebShop() {
        navigateTo(PropertyManager.getProperty("web.base.url"));

    }
}
