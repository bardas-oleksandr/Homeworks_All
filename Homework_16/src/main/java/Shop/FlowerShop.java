package Shop;

import Flowers.Flower;
import Interfaces.IService;
import ShopInterfaces.IOwner;
import ShopInterfaces.IProvider;
import ShopInterfaces.IBuyer;

import java.util.List;
import java.util.Map;

public class FlowerShop implements IOwner, IProvider, IBuyer {
    private double expenses;    //Расходы магазина
    private double income;  //Доход магазина
    private FlowerStore flowerStore;    //Хранилище цветов. Реализовано связным списком, в котором каждый элемент списка - стэк отдельного рода цветов
    private Bouquet bouquet;    //Текущий заказ

    public FlowerShop() {
        this.expenses = this.income = 0;
        this.bouquet = new Bouquet();
        this.flowerStore = new FlowerStore();
    }

    //Методы поставщика=================================================================================================
    //Метод доставки цветов в магазин
    public void deliverFlowers(Class flowerType, String color, double costPrice, int count) {
        this.flowerStore.add(flowerType, color, costPrice, count);
        this.expenses += count * costPrice;
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
        System.out.println("Total income: " + IService.round(this.income, 2));
        System.out.println("Total expences: " + IService.round(this.expenses, 2));
        System.out.println("Total profit: " + IService.round((this.income - this.expenses), 2));
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
        this.income += this.bouquet.clear();
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
}