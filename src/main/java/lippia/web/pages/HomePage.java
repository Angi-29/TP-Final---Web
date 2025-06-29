package lippia.web.pages;

import lippia.web.constants.HomePageConstants;
import com.crowdar.core.actions.WebActionManager;



public class HomePage {
    public static int getCantidadArrivals() {
        return WebActionManager.getElements((HomePageConstants.ARRIVALS_IMG_XPATH)).size();
    }

    public static boolean estaEnLaPaginaDelProducto() {
        try {
            WebActionManager.getElement(HomePageConstants.PRODUCT_DESCRIPTION_TITLE);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
