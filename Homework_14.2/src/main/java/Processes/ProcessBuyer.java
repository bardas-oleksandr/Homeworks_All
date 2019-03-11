package Processes;

import Flowers.Flower;
import Interfaces.IProcess;
import Interfaces.IService;
import Shop.FlowerShop;
import ShopExceptions.LogException;
import ShopExceptions.NoFlowersException;
import ShopInterfaces.IBuyer;

import java.io.IOException;

public class ProcessBuyer extends Process implements IProcess {
    private IBuyer buyer;
    public ProcessBuyer(FlowerShop shop){
        this.buyer = shop;
    }

    @Override
    public void start() {
        int choice = 0;
        do{
            int typesCount = this.buyer.getReadyStacks();   //Получаем количество категорий цветов, готовых для продажи
            IService.cleanConsole();
            System.out.println("Select type of flower");
            this.buyer.showForBuyer();
            System.out.println("0 - Exit");
            System.out.print("Your choice:");
            do {
                choice = IService.getInteger();
                if (choice < 0 || choice > typesCount) {
                    System.out.println("Incorrect input. Integer from the range 0.." + typesCount + " is expected.");
                    System.out.print("Try again: ");
                }
            } while (choice < 0 || choice > typesCount);
            if(choice > 0){
                Flower flower = this.buyer.getFlowerByBuyersIndex(choice - 1);
                Class type = flower.getClass();
                String color = flower.getColor();
                int count = 0;
                try {
                    int maxCount = this.buyer.howMany(type, color);
                    System.out.print("How many flowers do you want:");
                    do {
                        count = IService.getInteger();
                        if (count < 1 || count > maxCount) {
                            System.out.println("Incorrect input. Integer from the range 1.." + maxCount + " is expected.");
                            System.out.print("Try again: ");
                        }
                    } while (count < 1 || count > maxCount);
                    try {
                        this.buyer.addToBouquet(type, color, count);
                    } catch (LogException e) {
                        this.buyer.addLog(e.getMessage());
                    }
                    IService.cleanConsole();
                    this.buyer.showForBuyer();
                    System.out.println("Do you want to buy the bouquet?");
                    System.out.println("1 - YES");
                    System.out.println("0 - NO");
                    System.out.print("Your answer:");
                    int answer = 0;
                    do {
                        answer = IService.getInteger();
                        if (answer != 0 && answer != 1) {
                            System.out.println("Incorrect input. 0 or 1 is expected.");
                            System.out.print("Try again: ");
                        }
                    } while (answer != 0 && answer != 1);
                    if(answer == 1){
                        try {
                            this.buyer.sellBouquet();
                        } catch (LogException e) {
                            this.buyer.addLog(e.getMessage());
                        }
                        System.out.println("Congratulations! You have bought the bouquet.");
                    }else{
                        this.buyer.cleanBouquet();
                        System.out.println("Your purchase was canceled");
                    }
                    System.out.print("Press enter to continue");
                    try {
                        char str = (char) System.in.read();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } catch (NoFlowersException e) {    //Логика магазина не допустит повления такого исключения
                    this.buyer.addLog(e.getMessage());
                    System.out.println("Sorry, we have no " + color + " " + type.getName() + "s in our store.");
                    System.out.print("Press enter to continue");
                    try {
                        char str = (char) System.in.read();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        }while(choice != 0);
    }
}
