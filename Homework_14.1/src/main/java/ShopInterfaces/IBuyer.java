package ShopInterfaces;

import Flowers.Flower;
import ShopExceptions.LogException;
import ShopExceptions.NoFlowersException;

public interface IBuyer extends IShop {
    void sellBouquet() throws LogException;
    void addToBouquet(Class flowerType, String color, int count) throws LogException;
    int getReadyStacks();
    int howMany(Class flowerType, String color) throws NoFlowersException;
    void cleanBouquet();
    Flower getFlowerByBuyersIndex(int index);
    void showForBuyer();
}
