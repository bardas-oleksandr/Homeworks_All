package Processes;

import Flowers.Carnation;
import Flowers.Gladiolus;
import Flowers.RoseFlower;
import Flowers.Tulip;
import Interfaces.IProcess;
import Shop.FlowerShop;
import ShopExceptions.CountException;
import ShopExceptions.LogException;
import ShopExceptions.PriceException;
import ShopInterfaces.IProvider;

public class ProcessProvider extends Process implements IProcess {
    private IProvider provider;

    public ProcessProvider(FlowerShop shop){
        this.provider = shop;
    }

    @Override
    public void start() {
        final int TYPES_COUNT = 4;
        final int COLORS_COUNT = 4;
        int typeNum = 0;
        do{
            Class type = null;
            int colorNum = 0;
            String color = null;
            int count = 0;
            double costPrice = 0;
            Interfaces.IConsole.cleanConsole();
            this.provider.showForProvider();
            System.out.println("Select flower type to add");
            System.out.println("1 - Rose flower");
            System.out.println("2 - Tulip");
            System.out.println("3 - Carnation");
            System.out.println("4 - Gladioulus");
            System.out.println("0 - Exit");
            System.out.print("Your choice:");
            do {
                typeNum = Interfaces.IConsole.getInteger();
                if (typeNum < 0 || typeNum > TYPES_COUNT) {
                    System.out.println("Incorrect input. Integer from the range 0.." + TYPES_COUNT + " is expected.");
                    System.out.print("Try again: ");
                }
            } while (typeNum < 0 || typeNum > TYPES_COUNT);
            if(typeNum > 0){
                switch(typeNum){
                    case 1: type = RoseFlower.class; break;
                    case 2: type = Tulip.class; break;
                    case 3: type = Carnation.class; break;
                    case 4: type = Gladiolus.class; break;
                }
                System.out.println("Select color of the flower");
                System.out.println("1 - Red");
                System.out.println("2 - Yellow");
                System.out.println("3 - Orange");
                System.out.println("4 - White");
                System.out.print("Your choice:");
                do {
                    colorNum = Interfaces.IConsole.getInteger();
                    if (colorNum < 1 || colorNum > COLORS_COUNT) {
                        System.out.println("Incorrect input. Integer from the range 1.." + COLORS_COUNT + " is expected.");
                        System.out.print("Try again: ");
                    }
                } while (colorNum < 1 || colorNum > COLORS_COUNT);
                switch(colorNum){
                    case 1: color="red"; break;
                    case 2: color="yellow"; break;
                    case 3: color="orange"; break;
                    case 4: color="white"; break;
                }

                System.out.print("Set flowers amount:");
                do {
                    count = Interfaces.IConsole.getInteger();
                    if (count <= 0) {
                        System.out.println("Incorrect input. Positive integer value is expected.");
                        System.out.print("Try again: ");
                    }
                } while (count <= 0);

                System.out.print("Set cost price:");
                do {
                    costPrice = Interfaces.IConsole.getDouble();
                    if (costPrice <= 0) {
                        System.out.println("Incorrect input. Positive value is expected.");
                        System.out.print("Try again: ");
                    }
                } while (costPrice <= 0);

                try {
                    this.provider.deliverFlowers(type, color, costPrice, count);
                }
                catch (LogException e) {
                    this.provider.addLog(e.getMessage());
                }
            }
        }while(typeNum != 0);
    }
}
