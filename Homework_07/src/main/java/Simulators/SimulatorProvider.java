package Simulators;

import Flowers.Carnation;
import Flowers.Gladiolus;
import Provider.IProvider;
import Flowers.RoseFlower;
import Flowers.Tulip;
import ShopExceptions.LogException;
import ShopExceptions.PriceException;
import ShopExceptions.CountException;

import java.util.Random;

public class SimulatorProvider {
    public void generate(IProvider provider) throws LogException{
        String color = Interfaces.IConsole.generateColor();
        Random rnd = new Random();
        int count = 1 + rnd.nextInt(11);
        double costPrice = 0.01 + Interfaces.IConsole.generateDouble(2);
        int flowerTypeNumber= rnd.nextInt(4);
        tryDeliver(provider, flowerTypeNumber, count, costPrice, color);
    }

    private void tryDeliver(IProvider provider, int flowerTypeNumber, int count, double costPrice, String color) throws LogException{
        try{
            switch(flowerTypeNumber){
                case 0:
                    provider.deliverFlowers(RoseFlower.class,count,costPrice,color);
                    break;
                case 1:
                    provider.deliverFlowers(Gladiolus.class,count,costPrice,color);
                    break;
                case 2:
                    provider.deliverFlowers(Tulip.class,count,costPrice,color);
                    break;
                case 3:
                    provider.deliverFlowers(Carnation.class,count,costPrice,color);
                    break;
            }
        }
        catch(CountException e){
            System.out.println("\n====EXCEPTION====");
            System.out.println(e.getMessage());
            System.out.println("Details:");
            System.out.println("Delivering " + e.getColor() + " " + e.getFlowerType().getName());
            System.out.println("Flowers count = " + e.getCount() + "\n");
            throw new LogException(e);
        }
        catch(PriceException e){
            System.out.println("\n====EXCEPTION====");
            System.out.println(e.getMessage());
            System.out.println("Details:");
            System.out.println("Delivering " + e.getColor() + " " + e.getFlowerType().getName());
            System.out.println("Flowers cost price = " + e.getPrice() + "\n");
            throw new LogException(e);
        }
    }

    public void tryNegativeCostPrice(IProvider provider) throws LogException{
        String color = Interfaces.IConsole.generateColor();
        Random rnd = new Random();
        int count = rnd.nextInt(11);
        double costPrice = -Interfaces.IConsole.generateDouble(2);
        int flowerTypeNumber= rnd.nextInt(4);
        tryDeliver(provider, flowerTypeNumber, count, costPrice, color);
    }

    public void tryNegativeCount(IProvider provider) throws LogException{
        String color = Interfaces.IConsole.generateColor();
        Random rnd = new Random();
        int count = -rnd.nextInt(11);
        double costPrice = Interfaces.IConsole.generateDouble(2);
        int flowerTypeNumber= rnd.nextInt(4);
        tryDeliver(provider, flowerTypeNumber, count, costPrice, color);
    }
}
