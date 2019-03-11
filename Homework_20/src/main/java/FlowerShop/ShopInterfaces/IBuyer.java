package FlowerShop.ShopInterfaces;

import FlowerShop.Shop.Product;
import FlowerShop.Shop.ProductList;

import java.util.Map;

public interface IBuyer{
    void buy();
    void addToPurchaseList(String name, String color, double price, int count);
    void cleanPurchaseList();
    Map<Integer,ProductList<Product>> showForBuyer();
    int getAvailableCategories();
    int howMany(String name, String color);
}
