package Problems_01_and_03.ShopInterfaces;

public interface IProvider{
    void deliverProduct(String name, String color, double costPrice, int count);
    void showForProvider();
}
