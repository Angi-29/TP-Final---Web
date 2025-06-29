package lippia.web.services;

import com.crowdar.core.PropertyManager;
import com.crowdar.core.actions.ActionManager;
import com.crowdar.core.actions.WebActionManager;
import junit.framework.Assert;
import lippia.web.constants.LoginConstants;
import lippia.web.constants.RegistroConstants;

import static com.crowdar.core.actions.WebActionManager.navigateTo;

public class LoginService extends ActionManager {

    public static void navegarWeb() {
        navigateTo(PropertyManager.getProperty("web.base.url"));
    }

    public static void clickMiCuenta() {
        WebActionManager.click(LoginConstants.BTN_MI_CUENTA);
    }

    public static void inputCredenciales(String email, String password) {
        WebActionManager.setInput(LoginConstants.EMAIL_INPUT_ID, email);
        WebActionManager.setInput(LoginConstants.PASSWORD_INPUT_ID, password);
    }

    public static void clickBtn(String nomBtn) {
        WebActionManager.waitClickable(LoginConstants.BTN_LOGIN).click();
    }

    public static String obtenerMensaje() {
        return WebActionManager.getText(LoginConstants.LBL_NOMBRE_CORREO);
    }
}

