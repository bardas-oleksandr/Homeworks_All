package Shop;

import Flowers.Flower;
import Interfaces.IService;
import Logs.Logger;
import ShopExceptions.*;
import ShopInterfaces.IOwner;
import ShopInterfaces.IProvider;
import ShopInterfaces.IBuyer;

import java.io.*;

public class FlowerShop implements IOwner, IProvider, IBuyer {
    private double expenses;    //Расходы магазина
    private double income;  //Доход магазина
    private String path;     //Путь, по которому хранятся данные хранилища
    private FlowerStore flowerStore;    //Хранилище цветов. Реализовано связным списком, в котором каждый элемент списка - стэк отдельного рода цветов
    private Bouquet bouquet;    //Текущий заказ
    private Logger logger;  //Объект логгера

    public FlowerShop() {
        this.expenses = this.income = 0;
        this.bouquet = new Bouquet();
        this.path = "src/main/resources/data.txt";
        this.logger = new Logger();
        try (ObjectInputStream input = new ObjectInputStream(new FileInputStream(this.path))) {
            this.flowerStore = (FlowerStore) input.readObject();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            this.flowerStore = new FlowerStore();
            this.flowerStore.initByAnnotations();   //Если файла нет, инициализация из аннотаций
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Методы общего предназначения======================================================================================

    //Логирует событие
    public void addLog(String message) {
        this.logger.addLog(message);
    }

    //Методы поставщика=================================================================================================

    //Метод доставки цветов в магазин
    public void deliverFlowers(Class flowerType, String color, double costPrice, int count) throws LogException {
        try {
            this.flowerStore.putOnStack(flowerType, color, costPrice, count);
            this.externalize();
        } catch (PriceException | CountException e) {
            throw new LogException(e.getMessage());
        }
        this.expenses += count * costPrice;
        StringBuilder message = new StringBuilder("New consignment was delivered\n");
        message.append(color + " " + flowerType.getName() + "\t\tcount: " + count + "\t\tcost price: " + costPrice);
        throw new LogException(new LogException(message.toString()));
    }

    //Отображение цветочного магазина глазами поставщика. Поставщик видит:
    //1. Все цветы цветочного магазина
    //2. Только себестоимость цветов, без цены для покупателя
    @Override
    public void showForProvider() {
        System.out.println("=========================FLOWER STORE==================================");
        System.out.println(this.flowerStore.showForProvider());
        System.out.println("=======================================================================");
    }

    //Методы собственника===============================================================================================

    //Метод установки цены
    public void setPrice(Class flowerType, double price, String color) throws LogException {
        try {
            this.flowerStore.setPrice(flowerType, color, price);
        } catch (PriceException e) {
            throw new LogException(e.getMessage());
        }
        this.externalize();
        StringBuilder message = new StringBuilder("New price was established\n");
        message.append(color + " " + flowerType.getName() + "\t\tprice: " + price);
        throw new LogException(new LogException(message.toString()));
    }

    //Возвращает количество категорий цветов в хранилище цветов (категория определяется видом цветка и его цветом)
    public int getFlowerTypesCount() {
        return this.flowerStore.getStacksCount();
    }

    //Возвращает класс цветка, находящегося в стэке с заданным индексом
    public Class getClassByIndex(int index) {
        FlowerStack stack = this.flowerStore.getFlowerStacks()[index];
        return stack.getFlowerType();
    }

    //Возвращает цвет цветка, находящегося в стэке с заданным индексом
    public String getColorByIndex(int index) {
        FlowerStack stack = this.flowerStore.getFlowerStacks()[index];
        return stack.getColor();
    }

    public void showLogs() {
        System.out.println("=========================JOURNAL OF EVENTS=============================");
        this.logger.print();
        System.out.println("=======================================================================");
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
    public void showForOwner() {
        System.out.println("=========================FLOWER STORE==================================");
        System.out.println(this.flowerStore);
        System.out.println("=======================================================================");
    }

    //Методы покупателя=================================================================================================

    //Метод добавления цветов к заказу
    public void addToBouquet(Class flowerType, String color, int count) throws LogException {
        try {
            if (this.howMany(flowerType, color) >= count) {
                try {
                    for (int i = 0; i < count; i++) {
                        this.flowerStore.addToBouquet(this.bouquet, flowerType, color);
                        this.externalize();
                    }
                } catch (PriceException | NoFlowersException e) {
                    this.externalize();
                    throw new LogException(e.getMessage());
                }
            } else {
                String str = new String("Inadequate stock of " + color + " " + flowerType.getName() + " in the store");
                throw new LogException(str);
            }
        } catch (NoFlowersException e) {
            this.externalize();
            throw new LogException(e.getMessage());
        }
    }

    //Метод продажи букета (получаем деньги, очищаем букет, бросаем исключение для логирования)
    public void sellBouquet() throws LogException {
        for (int i = 0; i < this.bouquet.getBouquet().length; i++) {
            this.income += this.bouquet.getBouquet()[i].getPrice();
        }
        StringBuilder message = new StringBuilder("Next bouquet was sold\n" + this.bouquet);
        this.bouquet.clear();
        throw new LogException(new LogException(message.toString()));
    }

    //Возвращает количество категорий цветов, готовых к продаже (для которых установлена цена)
    public int getReadyStacks() {
        return this.flowerStore.getReadyStacks();
    }

    //Возвращает наявное количество цветов заданного вида и цвета
    public int howMany(Class flowerType, String color) throws NoFlowersException {
        return this.flowerStore.howMany(flowerType, color);
    }

    //Метод возвращение цветов с набранного букета обратно в хранилище цветов
    public void cleanBouquet() {
        Flower[] flowers = this.bouquet.getBouquet();
        this.flowerStore.putOnStack(flowers);
        this.bouquet.clear();
        this.externalize();
    }

    public Flower getFlowerByBuyersIndex(int index) {
        FlowerStack stack = this.flowerStore.getStackByBuyersIndex(index);
        return stack.getFlower();
    }

    //Отображение цветочного магазина глазами покупателя. Покупатель видит:
    //1. Только те цветы, на которые установлены цены
    //2. Только цену цветов, без себестоимости
    //3. Свой текущий заказ
    @Override
    public void showForBuyer() {
        System.out.println("=========================FLOWER STORE==================================");
        System.out.println(this.flowerStore.showForBuyer());
        System.out.println("=========================CURRENT BOUQUET===============================");
        System.out.println(this.bouquet);
        System.out.println("=======================================================================");
    }

    private void externalize(){
        try(ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(this.path))) {
            output.writeObject(this.flowerStore);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}