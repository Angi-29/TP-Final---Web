package utils;

import com.crowdar.driver.DriverManager;
import org.openqa.selenium.*;
import com.crowdar.core.actions.WebActionManager;

import java.text.DecimalFormat;

public class Utils {

    /**
     * Genera un correo aleatorio válido o inválido según el tipo especificado.
     */
    public static String correoAleatorio(String email) {
        int numero = (int) (Math.random() * 1000);
        switch (email) {
            case "emailOK":
                return email + "_" + numero + "@gmail.com";
            case "emailInvalidSinArroba":
                return email + "_" + numero + "gmail.com";
            default:
                return " ";
        }
    }

    /**
     * Formatea un número double con separador de miles y 2 decimales.
     */
    public static String formatiarNumero(Double valor) {
        DecimalFormat formato = new DecimalFormat("#,##0.00");
        return formato.format(valor);
    }

    /**
     * Pausa la ejecución en segundos.
     */
    public static void sleep(int segundos) {
        try {
            Thread.sleep(segundos * 1000L);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Hace scroll y clic por JavaScript en el elemento identificado por XPath.
     * Útil para evitar superposición de anuncios u otros elementos flotantes.
     */
    public static void scrollAndClickByJs(String xpath) {
        WebElement elemento = WebActionManager.getElement(xpath);
        if (elemento != null && elemento.isDisplayed()) {
            JavascriptExecutor jse = (JavascriptExecutor) DriverManager.getDriverInstance();
            jse.executeScript("arguments[0].scrollIntoView({block: 'center'});", elemento);
            jse.executeScript("arguments[0].click();", elemento);
        } else {
            throw new RuntimeException("No se pudo encontrar o visualizar el elemento para hacer clic con JS: " + xpath);
        }
    }

    /**
     * Hace scroll y clic sobre un dropdown tipo Select2.
     * Útil cuando el dropdown está fuera de vista o cubierto por elementos flotantes.
     *
     * @param dropdownContainerXpath XPath del contenedor del dropdown (por ejemplo: //*[@id="s2id_billing_country"])
     */
    public static void scrollAndClickDropdownSelect2(String dropdownContainerXpath) {
        WebElement dropdown = WebActionManager.getElement(dropdownContainerXpath);

        if (dropdown != null && dropdown.isDisplayed()) {
            JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriverInstance();
            js.executeScript("arguments[0].scrollIntoView({block: 'center'});", dropdown);
            js.executeScript("arguments[0].click();", dropdown);
        } else {
            throw new RuntimeException("No se pudo encontrar o visualizar el dropdown Select2: " + dropdownContainerXpath);
        }
    }

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

