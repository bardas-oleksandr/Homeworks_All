package Problem_03.ShopInterfaces;

import Problem_03.Flowers.Flower;

import java.util.List;
import java.util.Map;

public interface IProvider{
    void deliverFlowers(Class flowerType, String color, double costPrice, int count);
    Map<Integer,List<Flower>> showForProvider();
}
