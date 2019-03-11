package Problems_01_and_03.Shop;

import Interfaces.IService;

import java.util.Locale;
import java.util.ResourceBundle;

public class ShopBundle {
    public static final String SHOP_BUNDLE = "shopBundle";
    public static final String EXIT_MODE = "exit";
    public static final String CHOICE = "choice";
    public static final String BUYER_MODE="mode1";
    public static final String OWNER_MODE="mode2";
    public static final String PROVIDER_MODE="mode3";
    public static final String LANG_MODE="mode4";
    public static final String MAIN_TITLE="mainTitle";
    public static final String TRY_AGAIN = "tryAgain";
    public static final String BUY_BUY = "buybuy";
    public static final String ENG_MODE = "lang1";
    public static final String UKR_MODE = "lang2";
    public static final String RU_MODE = "lang3";
    public static final String STORE = "STORE";
    public static final String CURRENT_PURCHASE = "CURRENT_PURCHASE";
    public static final String THE_WORST = "THE_WORST";
    public static final String TOP = "TOP";
    public static final String CURRENT_RESULTS = "CURRENT_RESULTS";
    public static final String STORE_EMPTY = "StoreEmpty";
    public static final String PURCHASE_EMPTY = "PurchaseEmpty";
    public static final String SUMMARY_PRICE = "SummaryPrice";
    public static final String TOTAL_PRICE = "TotalPrice";
    public static final String SELECT_TYPE = "selectProductType";
    public static final String YES_CHOICE = "yes";
    public static final String NO_CHOICE = "no";
    public static final String FINAL_AGREEMENT = "finalAgreement";
    public static final String PURCHASE_CANCEL = "purchaseCancel";
    public static final String CONGRATULATIONS = "congratulations";
    public static final String HOW_MANY = "howMany";
    public static final String SELECT_OPER = "selectOperation";
    public static final String SET_PRICE_MODE = "setPrice";
    public static final String SHOW_RES_MODE = "showFinancialResults";
    public static final String SET_DEF_MODE = "setDefaultPrices";
    public static final String SHOW_ARCHIVE_MODE = "showArchive";
    public static final String DISCOUNT_SET_MODE = "discountSettings";
    public static final String SET_NEW_PRICE = "setNewPrice";
    public static final String DISCOUNT_VALUE = "discountValue";
    public static final String START_TIME = "startTime";
    public static final String END_TIME = "endTime";
    public static final String COST_PRICE = "costPrice";
    public static final String AMOUNT = "amount";
    public static final String UNIT_PRICE = "unitPrice";
    public static final String SET_FLOWERS_AMOUNT = "setFlowersAmount";
    public static final String SELECT_FLOWER_COLOR = "selectFlowerColor";
    public static final String SET_COST_PRICE = "setCostPrice";
    private static Locale[] supportedLocales;

    static{
        supportedLocales = new Locale[3];
        supportedLocales[0]=Locale.ENGLISH;
        supportedLocales[1]=new Locale("uk", "UA");
        supportedLocales[2]=new Locale("ru", "RU");
        Locale.setDefault(supportedLocales[0]);
    }

    public static String getString(String key){
        ResourceBundle resourceBundle = ResourceBundle.getBundle(SHOP_BUNDLE);
        return resourceBundle.getString(key);
    }

    public static void setDefaultLocale(int choice) {
        if(choice>=0 && choice<=2){
            Locale.setDefault(supportedLocales[choice]);
        }else{
            throw new IllegalArgumentException("Value from the range 0..2 is expected");
        }
    }
}
