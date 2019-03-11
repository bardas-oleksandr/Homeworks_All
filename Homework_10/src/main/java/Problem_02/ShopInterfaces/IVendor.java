package Problem_02.ShopInterfaces;

import Problem_02.ShopExceptions.LogException;
import Problem_02.ShopExceptions.NoFlowersException;
import Problem_02.ShopExceptions.PriceException;
import Problem_02.Shop.Bouquet;
import Problem_02.Shop.FlowerStore;

public interface IVendor {
    void sellBouquet() throws LogException;
    void addToBouquet(Class flowerType, String color) throws PriceException, NoFlowersException;
    boolean isEmpty();
    int howMany(Class flowerType, String color) throws NoFlowersException;
    Bouquet getBouquet();
    FlowerStore getStore();
}
