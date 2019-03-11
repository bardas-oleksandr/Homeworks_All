package FlowerShop.Processes;

import FlowerShop.Shop.Product;
import Interfaces.IService;
import FlowerShop.Shop.ShopBundle;
import FlowerShop.ShopInterfaces.IProcess;
import FlowerShop.Shop.FlowerShop;
import FlowerShop.Shop.ProductList;
import FlowerShop.ShopInterfaces.IOwner;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.Map;
import java.util.Properties;

public class ProcessOwner extends Process implements IProcess {
    private IOwner owner;

    public ProcessOwner(FlowerShop shop, String defPrices, String priceArchive) {
        super(defPrices,priceArchive);
        this.owner = shop;
    }

    @Override
    public void start() {
        int choice;
        final int OPER_COUNT = 5;
        final int SET_PRICE = 1;
        final int SHOW_RES = 2;
        final int SET_DEF_PRICE = 3;
        final int SHOW_ARCHIVE = 4;
        final int DISCOUNT = 5;
        final int EXIT = 0;

        do {
            IService.clearConsole();
            System.out.println(ShopBundle.getString(ShopBundle.SELECT_OPER));
            System.out.println(SET_PRICE + " " + ShopBundle.getString(ShopBundle.SET_PRICE_MODE));
            System.out.println(SHOW_RES + " " + ShopBundle.getString(ShopBundle.SHOW_RES_MODE));
            System.out.println(SET_DEF_PRICE + " " + ShopBundle.getString(ShopBundle.SET_DEF_MODE));
            System.out.println(SHOW_ARCHIVE + " " + ShopBundle.getString(ShopBundle.SHOW_ARCHIVE_MODE));
            System.out.println(DISCOUNT + " " + ShopBundle.getString(ShopBundle.DISCOUNT_SET_MODE));
            System.out.println(EXIT + " " + ShopBundle.getString(ShopBundle.EXIT_MODE));
            System.out.print(ShopBundle.getString(ShopBundle.CHOICE));
            choice = IService.getIntegerBounded(0, OPER_COUNT);
            switch (choice) {
                case 1: {
                    setPriceProcess();
                }
                break;
                case 2: {
                    this.owner.showResults();
                    IService.pressEnterToContinue();
                }
                break;
                case 3:
                {
                    this.setDefaultPriceProcess();
                }
                break;
                case 4:
                {
                    this.owner.showPriceArchive();
                    IService.pressEnterToContinue();
                }
                break;
                case 5:
                {
                    this.setDiscountsProcess();
                }
                break;
            }
        } while (choice != 0);
    }

    private void setPriceProcess() {
        final int EXIT = 0;
        int typeNum;
        do {
            IService.clearConsole();
            int typesCount = this.owner.getFlowerTypesCount();
            System.out.println(ShopBundle.getString(ShopBundle.SELECT_TYPE));
            Map<Integer,ProductList<Product>> choiceMap = this.owner.showForOwner();
            System.out.println(EXIT + " " + ShopBundle.getString(ShopBundle.EXIT_MODE));
            System.out.print(ShopBundle.getString(ShopBundle.CHOICE));
            typeNum = IService.getIntegerBounded(0, typesCount);
            if (typeNum > 0) {
                ProductList<Product> list = choiceMap.get(typeNum);
                if(list != null){
                    double price;
                    System.out.print(ShopBundle.getString(ShopBundle.SET_NEW_PRICE));
                    do {
                        price = IService.getDouble();
                        if (price <= 0) {
                            System.out.println("Incorrect input. Positive value is expected.");
                            System.out.print(ShopBundle.getString(ShopBundle.TRY_AGAIN));
                        }
                    } while (price <= 0);
                    owner.setPrice(list.name(), list.color(), price);
                }else{
                    System.out.println("You are not supposed to see it"); //Этого сообщение не должно никога появится
                }
            }
        } while (typeNum != 0);
    }

    private void setDefaultPriceProcess(){
        String productName;
        Properties properties = new Properties();
        do{
            IService.clearConsole();
            String[] types = {"Rose flower","Tulip","Carnation","Gladioulus"};
            productName = setStringParamProcess(types, "Select flower type to add");
            String color;
            if(productName != null){
                String[] colors = {"Red","Yellow","Orange","White"};
                color = setStringParamProcess(colors,"Select color of the flower").toLowerCase();
                Double defaultPrice = setDoubleParamProcess("Set default price:");
                properties.put(productName+color,defaultPrice.toString());
            }
        }while(productName != null);
        try (OutputStream out = new FileOutputStream(this.defaultPricesPath())) {
            properties.storeToXML(out, "Default prices");
        } catch (IOException e) {
            e.printStackTrace();
        }
        IService.pressEnterToContinue();
    }

    private void setDiscountsProcess(){
        double discount = this.setDoubleParamProcess(ShopBundle.getString(ShopBundle.DISCOUNT_VALUE));
        System.out.println(ShopBundle.getString(ShopBundle.START_TIME));
        Date begin = IService.getDate();
        System.out.println(ShopBundle.getString(ShopBundle.END_TIME));
        Date end = IService.getDate();
        long period = 24*60*60*1000;
        this.owner.setShedule(discount, begin, end, period);
        IService.pressEnterToContinue();
    }
}
