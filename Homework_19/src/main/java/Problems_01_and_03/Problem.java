package Problems_01_and_03;

import Interfaces.IProblem;
import Interfaces.IService;
import Problems_01_and_03.Shop.ShopBundle;
import Problems_01_and_03.ShopInterfaces.IProcess;
import Problems_01_and_03.ShopInterfaces.IProcessCreator;
import Problems_01_and_03.Shop.FlowerShop;

import java.io.*;
import java.util.Locale;
import java.util.ResourceBundle;

public class Problem implements IProblem {
    private final String pathSerialize = "src/main/resources/data.txt";    //Путь к сериализуемой информации

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
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(this.pathSerialize))) {
            shop = (FlowerShop) in.readObject();
        } catch (IOException e) {
            shop = new FlowerShop();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        shop.runDefaultProcess();

        if(shop != null){
            try{
                do {
                    IService.clearConsole();
                    System.out.println(ShopBundle.getString(ShopBundle.MAIN_TITLE));              //Заголовок
                    System.out.println(BUYER + " " + ShopBundle.getString(ShopBundle.BUYER_MODE));    //Вход для покупателя
                    System.out.println(OWNER + " " + ShopBundle.getString(ShopBundle.OWNER_MODE));    //Вход для собственника магазина
                    System.out.println(PROVIDER + " " + ShopBundle.getString(ShopBundle.PROVIDER_MODE)); //Вход для поставщика
                    System.out.println(SET_LANG + " " + ShopBundle.getString(ShopBundle.LANG_MODE)); //Выбор языка
                    System.out.println(EXIT + " " + ShopBundle.getString(ShopBundle.EXIT_MODE));      //Выход
                    System.out.print(ShopBundle.getString(ShopBundle.CHOICE));                   //Приглашение для ввода
                    do {
                        choice = IService.getInteger();
                        if (choice < 0 || choice > MODES_COUNT) {
                            System.out.println("Incorrect input. Integer from the range 0.." + MODES_COUNT + " is expected.");
                            System.out.print(ShopBundle.getString(ShopBundle.TRY_AGAIN));
                        }
                    } while (choice < 0 || choice > MODES_COUNT);
                    if (choice != EXIT) {
                        if (choice != SET_LANG) {
                            IProcess process = IProcessCreator.create(choice, shop);
                            process.start();
                        } else {
                            setDefaultLocaleProccess();
                        }
                    }
                } while (choice != EXIT);
            }finally{
                this.serializeShop(shop);
            }
        }else{
            System.out.println("Error while shop loading has occured");
        }
        System.out.println(ShopBundle.getString(ShopBundle.BUY_BUY));
    }

    private void serializeShop(FlowerShop shop) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(this.pathSerialize))) {
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
