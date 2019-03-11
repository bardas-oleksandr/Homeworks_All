package Simulators;

import Flowers.Carnation;
import Flowers.Gladiolus;
import Provider.IProvider;
import Flowers.RoseFlower;
import Flowers.Tulip;

import java.util.Random;

public class SimulatorProvider {
    public void generate(IProvider provider){
        Random rnd = new Random();
        int number = rnd.nextInt(6);
        String color = null;
        switch(number){
            case 0:
                color = "yellow";
                break;
            case 1:
                color = "red";
                break;
            case 2:
                color = "white";
                break;
            case 3:
                color = "orange";
                break;
            case 4:
                color = "blue";
                break;
            case 5:
                color = "purple";
                break;
            default:
                color = "cyan";
        }
        int count = rnd.nextInt(11);
        double costPrice = (double)rnd.nextInt(2) + ((double)rnd.nextInt(100))/100;
        number = rnd.nextInt(4);
        switch(number){
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
}
