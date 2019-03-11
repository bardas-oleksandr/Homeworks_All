package FlowerShop.ShopInterfaces;

public interface IProvider{
    void deliverProduct(String name, String color, double costPrice, int count, String defaultPricesPath);
    void showForProvider();
}
