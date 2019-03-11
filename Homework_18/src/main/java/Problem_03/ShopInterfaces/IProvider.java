package Problem_03.ShopInterfaces;

import Problem_03.Shop.Product;
import Problem_03.Shop.ProductList;

import java.util.Map;

public interface IProvider{
    void deliverProduct(String name, String color, double costPrice, int count);
    void showForProvider();
}
