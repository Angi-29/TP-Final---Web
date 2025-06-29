package lippia.web.constants;

import org.openqa.selenium.WebElement;

public class RegistroConstants {
    public static final String BTN_MENU_CUENTA = "xpath://*[@id=\"menu-item-50\"]/a";
    public static final String EMAIL_INPUT_ID = "id:reg_email";
    public static final String PASSWORD_INPUT_ID = "id:reg_password";
    public static final String BTN_REGISTER = "xpath://input[@name='register']";
    public static final String LBL_NOMBRE_CORREO="xpath://p/strong[contains(text(), '%s')]";
    public static final String LBL_HELLO_LOGIN ="xpath://p[contains(text(), 'Hello')]" ;
    public static String LBL_Error_MSJ = "xpath://*[contains(text(), 'Please provide a valid email address.')]" ;
}
