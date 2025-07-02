package lippia.web.constants;

public class HomePageConstants {
    public static final String ARRIVALS_IMG_XPATH = "xpath://ul[contains(@class,'products')]/li";

    public static final String PRODUCT_DESCRIPTION_TITLE = "xpath://*[@id='tab-description']/h2";
    public static final String TITULO_NOMBRE_PRODUCTO = "xpath://h1[@itemprop=\"name\" and @class=\"product_title entry-title\"]";
    public static final String INPUT_CANTIDAD_PRODUCTO = "xpath://input[@name=\"quantity\"]";
    public static final String BTN_CLIC_ADD_TO_BASKET = "xpath://button[text()='Add to basket']";
    public static final String MENSAJE_CARRITO = "xpath://div[contains(@class,'woocommerce-message')]";
    public static final String BTN_VIEW_BASKET = "xpath://*[@id=\"content\"]/div[1]/a";
    public static final String TOTAL_CON_IMPUESTOS_LOCATOR = "xpath://tr[@class='order-total']//span[@class='woocommerce-Price-amount amount']";
    public static final String BTN_CHECKOUT = "xpath://a[contains(@class, 'checkout') and contains(text(), 'Proceed to Checkout')]";
    public static final String INPUT_FIRST_NAME = "xpath://input[@id='billing_first_name']";
    public static final String INPUT_LAST_NAME = "xpath://input[@id='billing_last_name']";
    public static final String INPUT_EMAIL = "xpath://input[@id='billing_email']";
    public static final String INPUT_PHONE = "xpath://input[@id='billing_phone']";
    public static final String SELECT_COUNTRY = "id:s2id_billing_country";
    public static final String INPUT_COUNTRY_SEARCH = "xpath://div[contains(@id, 'select2-drop')]//input";
    public static final String INPUT_ADDRESS = "xpath://input[@id='billing_address_1']";
    public static final String INPUT_CITY = "xpath://input[@id='billing_city']";
    public static final String SELECT_STATE = "xpath://*[@id='s2id_billing_state']";
    public static final String INPUT_STATE_SEARCH = "xpath://div[contains(@id, 'select2-drop')]//input";
    //Localisadores country
    public static final String INPUT_POST_CODE = "xpath://input[@id='billing_postcode']";
    public static final String INPUT_SEARCH_COUNTRY = "id:s2id_autogen1_search";
    public static final String SELECT_MATCH_COUNTRY = "xpath://span[@class='select2-match']";
    //Localisadores State
    public static final String SELECT_STATE_COUNTRY = "id:select2-chosen-2";
    public static final String INPUT_SEARCH_STATE_COUNTRY = "id:s2id_autogen2_search";
    public static final String SELECT_MATCH_STATE_COUNTRY = "xpath://span[@class='select2-match']";
    public static final String BTN_PLACE_ORDER = "id:place_order";
    //Clic RadioButton selecion metodo de pago
    public static final String RBTN_Direct_Bank_Transfer = "id:payment_method_bacs";
    public static final String RBTN_payment_method_cheque = "id:payment_method_cheque";
    public static final String RBTN_payment_method_cod = "id:payment_method_cod";
    public static final String RBTN_payment_method_ppec_paypal = "id:payment_method_ppec_paypal";
    public static final String LBL_ORDER_NUMBER = "xpath://*[@id=\"page-35\"]/div/div[1]/ul[1]/li[1]/strong";
    public static final String LBL_ORDER_TOTAL = "xpath://li[@class='total']/strong/span";
    public static final String LBL_ORDER_PAYMETHOD = "xpath://li[@class='method']/strong";

    // Método para obtener el xpath dinámico del producto por nombre
    public static String getXpathProductoPorNombre(String nomProducto) {
        return "xpath://ul[contains(@class,'products')]//h3[text()='" + nomProducto + "']";
    }
}