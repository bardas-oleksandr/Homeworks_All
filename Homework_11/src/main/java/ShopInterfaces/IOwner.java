package ShopInterfaces;

import ShopExceptions.LogException;
import ShopExceptions.PriceException;
import Shop.FlowerStore;


public interface IOwner extends IShop {
    void setPrice(Class flowerType, double price, String color) throws LogException;
    int getFlowerTypesCount();
    Class getClassByIndex(int index);
    String getColorByIndex(int index);
    void showLogs();
    void showResults();
    void showForOwner();
}
