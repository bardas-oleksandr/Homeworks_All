package Processes;

import Interfaces.IProcess;
import Shop.FlowerShop;
import ShopExceptions.LogException;
import ShopExceptions.PriceException;
import ShopInterfaces.IOwner;

import java.io.IOException;

public class ProcessOwner extends Process implements IProcess {
    private IOwner owner;

    public ProcessOwner(FlowerShop shop) {
        this.owner = shop;
    }

    @Override
    public void start() {
        int choice = 0;
        final int OPER_COUNT = 3;
        do {
            Interfaces.IConsole.cleanConsole();
            System.out.println("Select operation");
            System.out.println("1 - Set price");
            System.out.println("2 - Show financial results");
            System.out.println("3 - Show journal of events");
            System.out.println("0 - Exit");
            System.out.print("Your choice:");
            do {
                choice = Interfaces.IConsole.getInteger();
                if (choice < 0 || choice > OPER_COUNT) {
                    System.out.println("Incorrect input. Integer from the range 0.." + OPER_COUNT + " is expected.");
                    System.out.print("Try again: ");
                }
            } while (choice < 0 || choice > OPER_COUNT);
            switch (choice) {
                case 1: {
                    setPriceProcess();
                }
                break;
                case 2: {
                    this.owner.showResults();
                    System.out.print("Press enter to continue");
                    try {
                        char str = (char) System.in.read();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                break;
                case 3: {
                    this.owner.showLogs();
                    System.out.print("Press enter to continue");
                    try {
                        char str = (char) System.in.read();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                break;
            }
        } while (choice != 0);
    }

    private void setPriceProcess() {
        int typeNum = 0;
        int typesCount = this.owner.getFlowerTypesCount();
        do {
            Interfaces.IConsole.cleanConsole();
            System.out.println("Select type of flower");
            this.owner.showForOwner();
            System.out.println("0 - Exit");
            System.out.print("Your choice:");
            do {
                typeNum = Interfaces.IConsole.getInteger();
                if (typeNum < 0 || typeNum > typesCount) {
                    System.out.println("Incorrect input. Integer from the range 0.." + typesCount + " is expected.");
                    System.out.print("Try again: ");
                }
            } while (typeNum < 0 || typeNum > typesCount);
            if (typeNum > 0) {
                Class type = null;
                String color = null;
                type = owner.getClassByIndex(typeNum - 1);
                color = owner.getColorByIndex(typeNum - 1);
                double price = 0;
                System.out.print("Set new price:");
                do {
                    price = Interfaces.IConsole.getDouble();
                    if (price <= 0) {
                        System.out.println("Incorrect input. Positive value is expected.");
                        System.out.print("Try again: ");
                    }
                } while (price <= 0);
                try {
                    owner.setPrice(type, price, color);
                } catch (LogException e) {
                    this.owner.addLog(e.getMessage());
                }
            }
        } while (typeNum != 0);
    }
}
