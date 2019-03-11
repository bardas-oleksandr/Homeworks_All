package FlowerShop;

import Interfaces.IProblem;
import Interfaces.IService;
import FlowerShop.Shop.ShopBundle;
import FlowerShop.ShopInterfaces.IProcess;
import FlowerShop.ShopInterfaces.IProcessCreator;
import FlowerShop.Shop.FlowerShop;

import java.io.*;
import java.util.Properties;

public class Problem implements IProblem {
    private final String SERIALIZE_PATH = "serializePath";    //Ключ для нахождения пути к сериализуемой информации
    private final String PROPERTIES_PATH = "src/main/resources/shop_paths.properties";  //Путь к файлу со всеми путями
    private final String DEFAULT_PRICES = "defaultPricesPath";    //Ключ к файлу с ценами по умолчанию
    private final String PRICE_ARCHIVE = "priceArchive";    //Ключ к файлу архива цен
    private final String ARCHIVE_DEST = "archiveDest";   //Ключ к папке с арзированной информацией
    private final String ARCHIVE_SOURCE = "archiveSource"; //Ключ к папке, содержимое которой будет архивироваться

    @Override
    public void solve() {
        final int MODES_COUNT = 4;
        final int BUYER = 1;
        final int OWNER = 2;
        final int PROVIDER = 3;
        final int SET_LANG = 4;
        final int EXIT = 0;
        int choice;
        FlowerShop shop = null;
        Properties properties = new Properties();   //Тут будут храниться пути ко всем файлам
        try (InputStream stream = new FileInputStream(PROPERTIES_PATH)) {
            properties.loadFromXML(stream);
            String serializedStore = properties.getProperty(this.SERIALIZE_PATH);
            try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(
                    serializedStore))) {
                shop = (FlowerShop) in.readObject();
            } catch (IOException e) {
                shop = new FlowerShop(properties.getProperty(this.DEFAULT_PRICES),
                        properties.getProperty(this.PRICE_ARCHIVE));
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            if(shop != null){
                String archiveSource = properties.getProperty(this.ARCHIVE_SOURCE); //Что будем архивировать
                String archiveDest = properties.getProperty(this.ARCHIVE_DEST); //Куда будем архивировать
                shop.runDefaultProcess(archiveSource,archiveDest);
                try{
                    do {
                        IService.clearConsole();
                        System.out.println(ShopBundle.getString(ShopBundle.MAIN_TITLE));  //Заголовок
                        System.out.println(BUYER + " " + ShopBundle.getString(ShopBundle.BUYER_MODE));    //Вход для покупателя
                        System.out.println(OWNER + " " + ShopBundle.getString(ShopBundle.OWNER_MODE));    //Вход для собственника магазина
                        System.out.println(PROVIDER + " " + ShopBundle.getString(ShopBundle.PROVIDER_MODE)); //Вход для поставщика
                        System.out.println(SET_LANG + " " + ShopBundle.getString(ShopBundle.LANG_MODE)); //Выбор языка
                        System.out.println(EXIT + " " + ShopBundle.getString(ShopBundle.EXIT_MODE));      //Выход
                        System.out.print(ShopBundle.getString(ShopBundle.CHOICE));                   //Приглашение для ввода
                        do {
                            choice = IService.getInteger();
                            if (choice < 0 || choice > MODES_COUNT) {
                                System.out.println("Incorrect input. Integer from the range 0.." + MODES_COUNT +
                                        " is expected.");
                                System.out.print(ShopBundle.getString(ShopBundle.TRY_AGAIN));
                            }
                        } while (choice < 0 || choice > MODES_COUNT);
                        if (choice != EXIT) {
                            if (choice != SET_LANG) {
                                String defaultPricesPath = properties.getProperty(this.DEFAULT_PRICES); //Путь к файлу с ценами по умолчанию
                                String priceArchivePath = properties.getProperty(this.PRICE_ARCHIVE); //Путь к архиву цен
                                IProcess process = IProcessCreator.create(choice, shop, defaultPricesPath, priceArchivePath);
                                process.start();
                            } else {
                                setDefaultLocaleProccess();
                            }
                        }
                    } while (choice != EXIT);
                }finally{
                    this.serializeShop(shop, properties);
                }
            }else{
                System.out.println("Error while shop loading has occured");
            }
            System.out.println(ShopBundle.getString(ShopBundle.BUY_BUY));
        } catch (IOException e) {
            System.out.println("Fatal error! Can't load properties file");
        }
    }

    private void serializeShop(FlowerShop shop, Properties properties) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(
                properties.getProperty(this.SERIALIZE_PATH)))) {
            out.writeObject(shop);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void setDefaultLocaleProccess() {
        final int EXIT = 0;
        final int ENG = 1;
        final int UKR = 2;
        final int RUS = 3;
        int choice;
        do {
            IService.clearConsole();
            System.out.println(ENG + " " + ShopBundle.getString(ShopBundle.ENG_MODE));
            System.out.println(UKR + " " + ShopBundle.getString(ShopBundle.UKR_MODE));
            System.out.println(RUS + " " + ShopBundle.getString(ShopBundle.RU_MODE));
            System.out.println(EXIT + " " + ShopBundle.getString(ShopBundle.EXIT_MODE));
            System.out.print(ShopBundle.getString(ShopBundle.CHOICE));
            choice = IService.getIntegerBounded(EXIT, RUS);
            if (choice != EXIT) {
                ShopBundle.setDefaultLocale(choice -1);
            }
        } while (choice != EXIT);
    }
}
