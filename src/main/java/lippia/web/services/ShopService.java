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
        navigateTo(PropertyManager.getProperty("web.base.url"));
    }

    public static void clicTituloProducto(String nomProducto) {
        // Encontrar todos los elementos que coinciden con el selector
        List<WebElement> elementos = WebActionManager.getElements(ShopConstants.LISTA_LINK_PRODUCTO);
        // Iterar sobre los elementos para encontrar el que contiene el texto específico en h3
        WebElement elementoDeseado = null;
        for (WebElement elemento : elementos) {
            WebElement h3 = elemento.findElement(By.tagName("h3"));
            if (h3.getText().equals(nomProducto)) {
                elementoDeseado = elemento;
                break; // Terminar la búsqueda una vez encontrado el elemento deseado
            }

        }
        // Hacer algo con el elemento deseado, por ejemplo, hacer clic en él, pero primero ubicamos el elemento en el sitio
        // esto se hace para que las publicidades no interfiera.
        if (elementoDeseado != null) {
            JavascriptExecutor jse = DriverManager.getDriverInstance();
            jse.executeScript("arguments[0].scrollIntoView()", new Object[]{elementoDeseado});
            elementoDeseado.click();

        } else {
            System.out.println("No se encontró ningún elemento con el texto 'Android Quick Start Guide'");
        }
    }

    public static void ordenarProducto(String ordenarProducto) {
        // Localizar el elemento <select>
        WebElement selectElemento = WebActionManager.waitVisibility(ShopConstants.SELECT_ORDER_ELEMENT);
        // Crear objeto Select
        Select select = new Select(selectElemento);
        // Seleccionar por texto visible
        select.selectByVisibleText(ordenarProducto); // Cambiá esto según lo que quieras seleccionar

    }

    public static String obtenerPrimerElementoDeListaDeProducto() {
        WebElement titulo = WebActionManager.waitVisibility(ShopConstants.LBL_PRIMER_ELEMENTO_LISTA).findElement(By.tagName("h3"));
        return titulo.getText();
    }
}
