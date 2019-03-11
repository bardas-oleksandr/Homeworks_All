package Problem_03.Shop;

import Problem_03.Flowers.Flower;
import Problem_03.ShopInterfaces.IOwner;
import Problem_03.ShopInterfaces.IProvider;
import Problem_03.ShopInterfaces.IBuyer;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FlowerShop implements IOwner, IProvider, IBuyer, Serializable {
    private FlowerStore flowerStore;    //Хранилище цветов. Реализовано связным списком, в котором каждый элемент списка - стэк отдельного рода цветов
    private Bouquet bouquet;    //Текущий заказ
    private Statistics statistics;  //Статистика по продажам

    public FlowerShop() {
        this.bouquet = new Bouquet();
        this.flowerStore = new FlowerStore();
        this.statistics = new Statistics();
    }

    //Методы поставщика=================================================================================================
    //Метод доставки цветов в магазин
    public void deliverFlowers(Class flowerType, String color, double costPrice, int count) {
        this.flowerStore.add(flowerType, color, costPrice, count);
        this.statistics.add(flowerType, color);
        this.statistics.addExpences(count * costPrice);
    }

    //Отображение цветочного магазина глазами поставщика. Поставщик видит:
    //1. Все цветы цветочного магазина
    //2. Только себестоимость цветов, без цены для покупателя
    @Override
    public Map<Integer,List<Flower>> showForProvider() {
        ChoiceMapWrapper wrapper = new ChoiceMapWrapper();
        System.out.println("=========================FLOWER STORE==================================");
        System.out.println(this.flowerStore.showForProvider(wrapper));
        System.out.println("=======================================================================");
        return wrapper.get();
    }

    //Методы собственника===============================================================================================
    //Метод установки цены
    public void setPrice(Class flowerType, String color, double price) {
        this.flowerStore.setPrice(flowerType, color, price);
    }

    public int getFlowerTypesCount(){
        return this.flowerStore.categories();
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
    public Map<Integer,List<Flower>> showForOwner() {
        ChoiceMapWrapper wrapper = new ChoiceMapWrapper();
        System.out.println("=========================FLOWER STORE==================================");
        System.out.println(this.flowerStore.showForOwner(wrapper));
        System.out.println("=======================================================================");
        return wrapper.get();
    }

    //Методы покупателя=================================================================================================
    //Метод добавления цветов к заказу
    public void addToBouquet(Class flowerType, String color, int count) {
        for(int i = 0; i < count; i++){
            Flower flower = this.flowerStore.removeFlower(flowerType, color);
            this.bouquet.addFlower(flower);
        }
    }

    //Метод продажи букета (получаем деньги, очищаем букет, бросаем исключение для логирования)
    public void sellBouquet() {
        double price = this.bouquet.getPrice();
        ArrayList<Flower> list = this.bouquet.getBouquet();
        double costPrice = 0;
        for(Flower flower: list){
            costPrice += flower.getCostPrice();
        }
        Flower flower = list.get(0);
        Class flowerType = flower.getClass();
        String color = flower.getColor();
        this.statistics.modify(flowerType, color, price - costPrice);
        this.statistics.addIncome(this.bouquet.clear());
    }

    //Метод возвращение цветов с набранного букета обратно в хранилище цветов
    public void cleanBouquet() {
        this.flowerStore.addAll(this.bouquet.getBouquet());
        this.bouquet.clear();
    }

    public int getReadyStacks(){
        return this.flowerStore.categoriesWithPrice();
    }

    public int howMany(Class type, String color){
        return this.flowerStore.flowersCount(type, color);
    }

    //Отображение цветочного магазина глазами покупателя. Покупатель видит:
    //1. Только те цветы, на которые установлены цены
    //2. Только цену цветов, без себестоимости
    //3. Свой текущий заказ
    @Override
    public Map<Integer,List<Flower>> showForBuyer() {
        ChoiceMapWrapper wrapper = new ChoiceMapWrapper();
        System.out.println("=========================FLOWER STORE==================================");
        System.out.println(this.flowerStore.showForBuyer(wrapper));
        System.out.println("=========================CURRENT BOUQUET===============================");
        System.out.println(this.bouquet);
        System.out.println("=======================================================================");
        return wrapper.get();
    }

    private void writeObject(ObjectOutputStream stream) throws IOException{
        stream.defaultWriteObject();
    }

    private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException{
        stream.defaultReadObject();
    }
}