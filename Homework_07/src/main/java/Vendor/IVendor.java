package Vendor;

import ShopExceptions.LogException;
import ShopExceptions.NoFlowersException;
import ShopExceptions.PriceException;
import Store.Bouquet;
import Store.FlowerStore;

public interface IVendor {
    void sellBouquet() throws LogException;
    void addToBouquet(Class flowerType, String color) throws PriceException, NoFlowersException;
    boolean isEmpty();
    int howMany(Class flowerType, String color) throws NoFlowersException;
    Bouquet getBouquet();
    FlowerStore getStore();
}
