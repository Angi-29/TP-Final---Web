package lippia.web.services;

import com.crowdar.core.PropertyManager;
import com.crowdar.core.actions.ActionManager;
import com.crowdar.core.actions.WebActionManager;
import lippia.web.constants.MyAccountConstants;

import static com.crowdar.core.actions.WebActionManager.navigateTo;

public class MyAccountService extends ActionManager {
    public static void clickMenuCuenta() {
        WebActionManager.click(MyAccountConstants.BTN_MENU_CUENTA);
    }

    public static void navegarWeb() {
        navigateTo(PropertyManager.getProperty("web.base.url"));
    }

    public static void clickMiCuenta() {
        WebActionManager.click(MyAccountConstants.BTN_MI_CUENTA);
    }

    public static void enterCredentials(String email, String password) {
        WebActionManager.waitClickable(MyAccountConstants.EMAIL_INPUT_ID);
        WebActionManager.setInput(MyAccountConstants.EMAIL_INPUT_ID, email);
        WebActionManager.waitClickable(MyAccountConstants.PASSWORD_INPUT_ID);
        WebActionManager.setInput(MyAccountConstants.PASSWORD_INPUT_ID, password);
        WebActionManager.waitClickable(MyAccountConstants.EMAIL_INPUT_ID);
    }

    public static void clicBtn(String nomBtn) {
        switch (nomBtn) {
            case "REGISTER":
                WebActionManager.waitClickable(MyAccountConstants.BTN_REGISTER).click();
                break;
            case "LOGIN":
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

    public static boolean buscarNombreCorreo(String email) {
        //Separamos el correo en dos parte separado por el @
        //en la posicion 0 del ventor esta el nombre del correo
        //en la posicion 1 del ventor esta gmail.com
        String correo = email.split("@")[0];

        try {
            String nomCorreoBuscar = String.format(MyAccountConstants.LBL_NOMBRE_CORREO, correo);
            WebActionManager.waitVisibility(nomCorreoBuscar);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean buscarHello() {
        try {
            WebActionManager.getElement(MyAccountConstants.LBL_HELLO_LOGIN);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean buscarErrorMsj() {
        try {
            WebActionManager.waitVisibility(MyAccountConstants.LBL_Error_MSJ);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static void ingresarEmailPass(String email, String password) {
        WebActionManager.waitClickable(MyAccountConstants.TXT_EMAIL_INPUT_ID);
        WebActionManager.setInput(MyAccountConstants.TXT_EMAIL_INPUT_ID, email);
        WebActionManager.waitClickable(MyAccountConstants.TXT_PASSWORD_INPUT_ID);
        WebActionManager.setInput(MyAccountConstants.TXT_PASSWORD_INPUT_ID, password);
    }


    public static boolean buscarMsjErrorLogin(String msj) {
        try {
            String lblMsjError = String.format(MyAccountConstants.LBL_ERROR_LOGIN, msj);
            WebActionManager.waitVisibility(lblMsjError);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
