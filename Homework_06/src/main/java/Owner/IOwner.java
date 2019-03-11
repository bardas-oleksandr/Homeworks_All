package Owner;

import Store.FlowerStore;

public interface IOwner {
    void setPrice(Class flowerType, double price, String color);
    void showAll();
    FlowerStore getStore();
    double getProfit();
}
