package Problem_03.Shop;

import Problem_03.ShopInterfaces.IOwner;
import Problem_03.ShopInterfaces.IProvider;
import Problem_03.ShopInterfaces.IBuyer;

import java.io.*;
import java.util.*;

public class FlowerShop implements IOwner, IProvider, IBuyer, Serializable {
    private Store store;    //Хранилище товаров
    private Purchase<ProductList<Product>> purchase;    //Текущий заказ
    private Statistics statistics;  //Статистика по продажам

    public FlowerShop() {
        this.purchase = new Purchase();
        this.store = new Store();
        this.statistics = new Statistics();
        this.defaultPrices();
    }

    public void setShedule(double discount, Date dropTime, Date comeBackTime, long period){
        this.store.setShedule(discount, dropTime, comeBackTime, period);
    }

    public void showPriceArchive(){
        this.store.priceLogger().currentPrices();
    }

    public void setDefaultPrices(Properties properties){
        this.store.saveDefaultPrices(properties);
    }

    private void defaultPrices(){
        this.store.checkPrices();
    }

    //Методы поставщика=================================================================================================
    //Метод доставки товара в магазин
    @Override
    public void deliverProduct(String productName, String color, double costPrice, int count) {
        this.store.add(productName, color, costPrice, count);
        this.statistics.add(productName, color);
        this.statistics.addExpences(count * costPrice);
        this.defaultPrices();
    }

    //Отображение цветочного магазина глазами поставщика. Поставщик видит:
    //1. Все цветы цветочного магазина
    //2. Только себестоимость цветов, без цены для покупателя
    @Override
    public void showForProvider() {
        System.out.println("================================STORE==================================");
        System.out.println(this.store.showForProvider());
        System.out.println("=======================================================================");
    }

    //Методы собственника===============================================================================================
    //Метод установки цены
    @Override
    public void setPrice(String name, String color, double price) {
        this.store.setPrice(name, color, price);
    }

    public int getFlowerTypesCount(){
        return this.store.categories();
    }

    //Отображение результатов деятельности (доходы, расходы, прибыль)
    public void showResults() {
        System.out.println("=========================CURRENT RESULTS===============================");
        this.statistics.showGeneral();
        System.out.println("=========================TOP 3=========================================");
        System.out.println(statistics.top(3, true));
        System.out.println("=========================THE WORST 3===================================");
        System.out.println(statistics.top(3, false));
        System.out.println("=======================================================================");
    }

    //Отображение цветочного магазина глазами собственника. Собственник видит:
    //1. Все цветы цветочного магазина во всех деталях
    @Override
    public Map<Integer,ProductList<Product>> showForOwner() {
        ChoiceMapWrapper wrapper = new ChoiceMapWrapper();
        System.out.println("=========================FLOWER STORE==================================");
        System.out.println(this.store.showForOwner(wrapper));
        System.out.println("=======================================================================");
        return wrapper.get();
    }

    //Методы покупателя=================================================================================================
    //Метод добавления цветов к заказу
    @Override
    public void addToPurchaseList(String name, String color, double price, int count) {
        ProductList<Product> productList = new ProductList<>(name, color, price, this.store.priceLogger());
        for(int i = 0; i < count; i++){
            Optional<Product> optional = this.store.removeProduct(name, color);
            optional.ifPresent(productList::add);   //Ссылка на метод
        }
        this.purchase.add(productList);
    }

    //Метод продажи букета (получаем деньги, очищаем букет)
    public void buy() {
        double price = this.purchase.getTotalPrice();
        ArrayList<ProductList<Product>> list = this.purchase.getPurchaseList();
        double costPrice = 0;
        for(ProductList<Product> productList: list){
            for(Product product: productList){
                costPrice += product.costPrice();
            }
        }
        ProductList productList = list.get(0);
        this.statistics.modify(productList.name(), productList.color(), price - costPrice);
        this.statistics.addIncome(this.purchase.clear());
    }

    //Метод возвращение цветов с набранного букета обратно в хранилище цветов
    public void cleanPurchaseList() {
        this.store.addAll(this.purchase.getPurchaseList());
        this.purchase.clear();
    }

    public int getAvailableCategories(){
        return this.store.categoriesWithPrice();
    }

    public int howMany(String name, String color){
        return this.store.productsAmount(name, color);
    }

    //Отображение цветочного магазина глазами покупателя. Покупатель видит:
    //1. Только те цветы, на которые установлены цены
    //2. Только цену цветов, без себестоимости
    //3. Свой текущий заказ
    @Override
    public Map<Integer,ProductList<Product>> showForBuyer() {
        ChoiceMapWrapper wrapper = new ChoiceMapWrapper();
        System.out.println("=============================STORE======================================");
        System.out.println(this.store.showForBuyer(wrapper));
        System.out.println("=========================CURRENT PURCHASE===============================");
        System.out.println(this.purchase);
        System.out.println("========================================================================");
        return wrapper.get();
    }

    private void writeObject(ObjectOutputStream stream) throws IOException{
        stream.defaultWriteObject();
    }

    private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException{
        stream.defaultReadObject();
    }
}