package utils;

import com.crowdar.core.actions.WebActionManager;
import com.crowdar.driver.DriverManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

public class Select2Utils {

    /**
     * Selecciona una opción visible dentro de un dropdown tipo Select2.
     *
     * @param dropdownLocator locator del contenedor (xpath o css)
     * @param opcionVisible texto visible de la opción a seleccionar
     */
    public static void seleccionarOpcion(String dropdownLocator, String opcionVisible) {
        WebElement dropdown = WebActionManager.getElement(dropdownLocator);
        ((JavascriptExecutor) DriverManager.getDriverInstance())
                .executeScript("var evt = new MouseEvent('mousedown', {bubbles: true}); arguments[0].dispatchEvent(evt);", dropdown);

        WebElement input = WebActionManager.getElement("css:.select2-input");
        input.sendKeys(opcionVisible);

        WebElement option = WebActionManager.getElement("xpath://div[contains(@class,'select2-result-label') and text()='" + opcionVisible + "']");
        option.click();
    }
}

