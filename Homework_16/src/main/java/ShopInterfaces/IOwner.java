package ShopInterfaces;

import Flowers.Flower;

import java.util.List;
import java.util.Map;

public interface IOwner{
    void setPrice(Class flowerType, String color, double price);
    void showResults();
    int getFlowerTypesCount();
    Map<Integer,List<Flower>> showForOwner();
}
