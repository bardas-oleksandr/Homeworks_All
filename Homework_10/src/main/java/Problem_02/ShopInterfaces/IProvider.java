package Problem_02.ShopInterfaces;

import Problem_02.ShopExceptions.CountException;
import Problem_02.ShopExceptions.LogException;
import Problem_02.ShopExceptions.PriceException;

public interface IProvider {
    void deliverFlowers(Class flowerType, int count, double costPrice, String color) throws CountException, PriceException, LogException;
}
