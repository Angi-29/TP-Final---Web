package lippia.web.constants;

public class ProductContants {
    public static final String TITULO_NOMBRE_PRODUCTO = "xpath://h1[@itemprop=\"name\" and @class=\"product_title entry-title\"]";
    public static final String INPUT_CANTIDAD_PRODUCTO = "xpath://input[@name=\"quantity\"]";
    public static final String BTN_CLIC_ADD_TO_BASKET = "xpath://button[text()='Add to basket']";
    public static final String MSJ_PRODUCTO_AGREGADO = "xpath://div[text()=' %s']";
    public static final String PRESIO_FINAL_PRODUCTO = "xpath:(//span[@class='woocommerce-Price-amount amount'])[1]";
    public static final String LBL_SUB_TOTAL = "xpath://span[@class='amount']";
}
