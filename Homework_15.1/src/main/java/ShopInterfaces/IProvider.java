package ShopInterfaces;

import ShopExceptions.CountException;
import ShopExceptions.LogException;
import ShopExceptions.PriceException;

public interface IProvider extends IShop {
    void deliverFlowers(Class flowerType, String color, double costPrice, int count) throws LogException;
    int getFlowerTypesCount();
    void showForProvider();
}
