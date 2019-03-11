package Owner;

import ShopExceptions.LogException;
import ShopExceptions.PriceException;
import Store.FlowerStore;


public interface IOwner {
    void setPrice(Class flowerType, double price, String color) throws PriceException, LogException;
    void showAll();
    FlowerStore getStore();
    double getProfit();
}
