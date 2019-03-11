package Simulators;

import ShopExceptions.LogException;
import ShopExceptions.PriceException;
import Store.FlowerStack;
import Owner.IOwner;
import java.util.Random;

public class SimulatorOwner {
    public void generatePrice(IOwner owner) throws LogException{   //Метод перебирает все массивы цветов и для каждого типа цветка каждого цвета генерирует отдельную случайную цену (выше себестоимости)
        Random rnd = new Random();
        FlowerStack pCurr = owner.getStore().getPRoot();
        StringBuilder message = new StringBuilder();
        for(int i = 0; i < owner.getStore().getStacksCount(); i++){
            double price = pCurr.getFlower(0).getCostPrice() + (double)rnd.nextInt(1) + ((double)rnd.nextInt(100))/100;
            price = Interfaces.IConsole.round(price,2);
            try{
                owner.setPrice(pCurr.getFlower().getClass(), price, pCurr.getFlower(0).getColor());
            }
            catch(PriceException e){
                System.out.println("\n====EXCEPTION====");
                System.out.println(e.getMessage());
                System.out.println("Details:");
                System.out.println("Type of flowers: " + e.getColor() + " " + e.getFlowerType().getName());
                System.out.println("Attempt to establish price at the level of " + e.getPrice() + "$ was made\n");
                throw new LogException(e);
            }
            catch(LogException e){
                message.append(e.getMessage());
            }
            pCurr = pCurr.getPNext();
        }
        throw new LogException(new LogException(message.toString()));
    }

    //Метод для имитации "неправильного поведения" хозяина, когда он задает отрицательную цену на цветы
    //Для примера отрицательная цена задается на первый же тип цветов в магазине
    public void tryNegativePrice(IOwner owner) throws LogException{
        Random rnd = new Random();
        double price = -(rnd.nextInt(3));
        FlowerStack pCurr = owner.getStore().getPRoot();
        try{
            for(int j =0; j < pCurr.getCount();j++){
                owner.setPrice(pCurr.getFlower().getClass(), price, pCurr.getFlower(j).getColor());
            }
        }
        catch(PriceException e){
            System.out.println("\n====EXCEPTION====");
            System.out.println(e.getMessage());
            System.out.println("Details:");
            System.out.println("Type of flowers: " + e.getColor() + " " + e.getFlowerType().getName());
            System.out.println("Attempt to establish price at the level of " + e.getPrice() + "$ was made\n");
            throw new LogException(e);
        }
    }
}
