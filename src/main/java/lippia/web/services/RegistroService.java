package lippia.web.services;

import com.crowdar.core.actions.WebActionManager;
import lippia.web.constants.LoginConstants;
import lippia.web.constants.RegistroConstants;
import org.openqa.selenium.By;

public class RegistroService {
    public static void clickMenuCuenta() {
        WebActionManager.click(RegistroConstants.BTN_MENU_CUENTA);
    }

    public static void enterCredentials(String email, String password) {
        WebActionManager.waitClickable(RegistroConstants.EMAIL_INPUT_ID);
        WebActionManager.setInput(RegistroConstants.EMAIL_INPUT_ID, email);
        WebActionManager.waitClickable(RegistroConstants.PASSWORD_INPUT_ID);
        WebActionManager.setInput(RegistroConstants.PASSWORD_INPUT_ID, password);
    }

    public static void clicBtn(String nomBtn) {
        switch (nomBtn) {
            case "REGISTER":
                WebActionManager.waitClickable(RegistroConstants.BTN_REGISTER).click();
                break;
            case "opcion2":
                System.out.println("Seleccionaste la opción 2");
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
            String nomCorreoBuscar=String.format(RegistroConstants.LBL_NOMBRE_CORREO,correo);
            WebActionManager.waitVisibility(nomCorreoBuscar);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public static boolean buscarHello() {
        try {
            WebActionManager.getElement(RegistroConstants.LBL_HELLO_LOGIN);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public static boolean buscarErrorMsj() {
        try {
            WebActionManager.waitVisibility(RegistroConstants.LBL_Error_MSJ);
            return true;
        }catch (Exception e){
            return false;
        }
    }


}
