package Problem_03.Processes;

import Problem_03.Shop.Product;
import Interfaces.IService;
import Problem_03.ShopInterfaces.IProcess;
import Problem_03.Shop.FlowerShop;
import Problem_03.Shop.ProductList;
import Problem_03.ShopInterfaces.IOwner;

import java.io.IOException;
import java.util.Date;
import java.util.Map;
import java.util.Properties;

public class ProcessOwner extends Process implements IProcess {
    private IOwner owner;

    public ProcessOwner(FlowerShop shop) {
        this.owner = shop;
    }

    @Override
    public void start() {
        int choice;
        final int OPER_COUNT = 5;
        do {
            IService.clearConsole();
            System.out.println("Select operation");
            System.out.println("1 - Set price");
            System.out.println("2 - Show financial results");
            System.out.println("3 - Set default prices");
            System.out.println("4 - Show the archive of price changing");
            System.out.println("5 - Discount settings");
            System.out.println("0 - Exit");
            System.out.print("Your choice:");
            choice = IService.getIntegerBounded(0, OPER_COUNT);
            switch (choice) {
                case 1: {
                    setPriceProcess();
                }
                break;
                case 2: {
                    this.owner.showResults();
                    System.out.print("Press enter to continue");
                    try {
                        System.in.read();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
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
        int typeNum;
        do {
            IService.clearConsole();
            int typesCount = this.owner.getFlowerTypesCount();
            System.out.println("Select type of flower");
            Map<Integer,ProductList<Product>> choiceMap = this.owner.showForOwner();
            System.out.println("0 - Exit");
            System.out.print("Your choice:");
            typeNum = IService.getIntegerBounded(0, typesCount);
            if (typeNum > 0) {
                ProductList<Product> list = choiceMap.get(typeNum);
                if(list != null){
                    double price;
                    System.out.print("Set new price:");
                    do {
                        price = IService.getDouble();
                        if (price <= 0) {
                            System.out.println("Incorrect input. Positive value is expected.");
                            System.out.print("Try again: ");
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
        this.owner.setDefaultPrices(properties);
        IService.pressEnterToContinue();
    }

    private void setDiscountsProcess(){
        double discount = this.setDoubleParamProcess("Discount value, %:");
        System.out.println("Setting start time");
        Date begin = IService.getDate();
        System.out.println("Setting end time");
        Date end = IService.getDate();
        long period = 24*60*60*1000;
        this.owner.setShedule(discount, begin, end, period);
        IService.pressEnterToContinue();
    }
}
