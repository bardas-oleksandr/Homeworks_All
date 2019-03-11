package Provider;

import ShopExceptions.CountException;
import ShopExceptions.LogException;
import ShopExceptions.PriceException;

public interface IProvider {
    void deliverFlowers(Class flowerType, int count, double costPrice, String color) throws CountException, PriceException, LogException;
}
