package Problem_02.ShopInterfaces;

import Problem_02.ShopExceptions.LogException;
import Problem_02.ShopExceptions.PriceException;
import Problem_02.Shop.FlowerStore;


public interface IOwner {
    void setPrice(Class flowerType, double price, String color) throws PriceException, LogException;
    void showAll();
    FlowerStore getStore();
    double getProfit();
}
