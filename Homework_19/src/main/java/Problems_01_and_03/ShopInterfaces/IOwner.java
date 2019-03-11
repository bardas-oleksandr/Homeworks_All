package Problems_01_and_03.ShopInterfaces;

import Problems_01_and_03.Shop.Product;
import Problems_01_and_03.Shop.ProductList;

import java.util.Date;
import java.util.Map;
import java.util.Properties;

public interface IOwner{
    void setPrice(String name, String color, double price);
    void showResults();
    int getFlowerTypesCount();
    Map<Integer,ProductList<Product>> showForOwner();
    void setDefaultPrices(Properties properties);
    void showPriceArchive();
    void setShedule(double discount, Date dropTime, Date comeBackTime, long period);
}
