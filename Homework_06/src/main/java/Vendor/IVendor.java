package Vendor;

import Store.Bouquet;

public interface IVendor {
    void sellBouquet();
    boolean addToBouquet(Class flowerType, String color);
    boolean isEmpty();
    int howMany(Class flowerType, String color);
    Bouquet getBouquet();
}
