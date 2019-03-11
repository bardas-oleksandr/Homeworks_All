package Problems_01_and_03.ShopInterfaces;

import Problems_01_and_03.Shop.Product;
import Problems_01_and_03.Shop.ProductList;

import java.util.Map;

public interface IBuyer{
    void buy();
    void addToPurchaseList(String name, String color, double price, int count);
    void cleanPurchaseList();
    Map<Integer,ProductList<Product>> showForBuyer();
    int getAvailableCategories();
    int howMany(String name, String color);
}
