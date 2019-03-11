package Problem_03;

import Interfaces.IProblem;
import Interfaces.IService;
import Problem_03.ShopInterfaces.IProcess;
import Problem_03.ShopInterfaces.IProcessCreator;
import Problem_03.Shop.FlowerShop;

import java.io.*;

public class Problem implements IProblem {
    private String pathSerialize ="src/main/resources/data.txt";    //Путь к сериализуемой информации

    @Override
    public void solve() {
        final int MODES_COUNT = 3;
        int choice;
        FlowerShop shop = null;
        try(ObjectInputStream in = new ObjectInputStream(new FileInputStream(this.pathSerialize))) {
            shop = (FlowerShop) in.readObject();
        } catch (IOException e) {
            shop = new FlowerShop();
        }catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try{
            do {
                IService.clearConsole();
                System.out.println("CHOOSE THE ENTRANCE MODE");
                System.out.println("1 - Buyer");        //Вход для покупателя
                System.out.println("2 - Owner");        //Вход для собственника магазина
                System.out.println("3 - Provider");     //Вход для поставщика
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
                    IProcess process = IProcessCreator.create(choice, shop);
                    process.start();
                }
            } while (choice != 0);
            System.out.println("Buy-buy!");
        }finally{
            this.serializeShop(shop);
        }
    }

    private void serializeShop(FlowerShop shop){
        try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(this.pathSerialize))) {
            out.writeObject(shop);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
