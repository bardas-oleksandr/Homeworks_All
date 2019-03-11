package Processes;

import Flowers.Flower;
import Interfaces.IService;
import Interfaces.IProcess;
import Shop.FlowerShop;
import ShopInterfaces.IOwner;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class ProcessOwner extends Process implements IProcess {
    private IOwner owner;

    public ProcessOwner(FlowerShop shop) {
        this.owner = shop;
    }

    @Override
    public void start() {
        int choice = 0;
        final int OPER_COUNT = 2;
        do {
            IService.cleanConsole();
            System.out.println("Select operation");
            System.out.println("1 - Set price");
            System.out.println("2 - Show financial results");
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
        do {
            IService.cleanConsole();
            int typesCount = this.owner.getFlowerTypesCount();
            System.out.println("Select type of flower");
            Map<Integer,List<Flower>> choiceMap = this.owner.showForOwner();
            System.out.println("0 - Exit");
            System.out.print("Your choice:");
            typeNum = IService.getIntegerBounded(0, typesCount);
            if (typeNum > 0) {
                List<Flower> list = choiceMap.get(typeNum);
                if(list != null & list.size() > 0){
                    Flower flower = list.get(0);
                    Class type = flower.getClass();
                    String color = flower.getColor();
                    double price = 0;
                    System.out.print("Set new price:");
                    do {
                        price = IService.getDouble();
                        if (price <= 0) {
                            System.out.println("Incorrect input. Positive value is expected.");
                            System.out.print("Try again: ");
                        }
                    } while (price <= 0);
                    owner.setPrice(type, color, price);
                }else{
                    System.out.println("You are not supposed to see it"); //Этого сообщение не должно никога появится
                }
            }
        } while (typeNum != 0);
    }
}
