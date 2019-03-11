package Simulators;

import Store.FlowerStack;
import Owner.IOwner;
import java.util.Random;

public class SimulatorOwner {
    public void generatePrice(IOwner owner){   //Метод перебирает все массивы цветов и для каждого типа цветка каждого цвета генерирует отдельную случайную цену (выше себестоимости)
        Random rnd = new Random();
        FlowerStack pCurr = owner.getStore().getPRoot();
        for(int i = 0; i < owner.getStore().getStacksCount(); i++){
            for(int j =0; j < pCurr.getCount();j++){
                double price = pCurr.getFlower(j).getCostPrice() + (double)rnd.nextInt(1) + ((double)rnd.nextInt(100))/100;
                price = Interfaces.IConsole.round(price,2);
                owner.setPrice(pCurr.getFlower().getClass(),price,pCurr.getFlower(j).getColor());
            }
            pCurr = pCurr.getPNext();
        }
    }
}
