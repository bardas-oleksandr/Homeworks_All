package Problem_03.ShopInterfaces;

import Problem_03.Flowers.Flower;

import java.util.List;
import java.util.Map;

public interface IBuyer{
    void sellBouquet();
    void addToBouquet(Class flowerType, String color, int count);
    void cleanBouquet();
    Map<Integer,List<Flower>> showForBuyer();
    int getReadyStacks();
    int howMany(Class type, String color);
}
