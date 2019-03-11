package FlowerShop.ShopInterfaces;

import FlowerShop.Shop.Product;
import FlowerShop.Shop.ProductList;

import java.util.Date;
import java.util.Map;
import java.util.Properties;

public interface IOwner{
    void setPrice(String name, String color, double price);
    void showResults();
    int getFlowerTypesCount();
    Map<Integer,ProductList<Product>> showForOwner();
    void showPriceArchive();
    void setShedule(double discount, Date dropTime, Date comeBackTime, long period);
}
