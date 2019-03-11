import Interfaces.*;
import Shop.FlowerShop;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        final int MODES_COUNT = 3;
        int choice = 0;
        FlowerShop shop = new FlowerShop();
        do {
            IService.cleanConsole();
            System.out.println("CHOOSE THE ENTRANCE MODE");
            System.out.println("1 - Buyer");        //¬ход дл€ покупател€
            System.out.println("2 - Owner");        //¬ход дл€ собственника магазина
            System.out.println("3 - Provider");     //¬ход дл€ поставщика
            System.out.println("0 - Exit");
            System.out.print("Your choice:");
            do {
                choice = IService.getInteger();
                if (choice < 0 || choice > MODES_COUNT) {
                    System.out.println("Incorrect input. Integer from the range 0.." + MODES_COUNT + " is expected.");
                    System.out.print("Try again: ");
                }
            } while (choice < 0 || choice > MODES_COUNT);
            if (choice != 0) {
                IProcess process = ICreator.create(choice, shop);
                process.start();
            }
        } while (choice != 0);
        System.out.println("Buy-buy!");
    }
}