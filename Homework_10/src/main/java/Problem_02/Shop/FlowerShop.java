package Problem_02.Shop;

import Problem_02.ShopInterfaces.IOwner;
import Problem_02.ShopInterfaces.IProvider;
import Problem_02.ShopExceptions.CountException;
import Problem_02.ShopExceptions.LogException;
import Problem_02.ShopExceptions.NoFlowersException;
import Problem_02.ShopExceptions.PriceException;
import Problem_02.ShopInterfaces.IVendor;

public class FlowerShop implements IOwner, IProvider, IVendor {
    private double expenses;
    private double income;
    private FlowerStore flowerStore;    //Реализован связным списком. Где каждый элемент списка - стэк отдельного рода цветов
    private Bouquet bouquet;

    public FlowerShop() {
        this.expenses = this.income = 0;
        this.bouquet = new Bouquet();
        this.flowerStore = new FlowerStore();
    }

    public double getProfit() {
        return this.income - this.expenses;
    }

    public void deliverFlowers(Class flowerType, int count, double costPrice, String color) throws PriceException, CountException, LogException {  //Метод для добавления цветов в связный список массивов цветов
        this.flowerStore.putOnStack(flowerType, count, costPrice, color);
        this.expenses += count * costPrice;
        StringBuilder message = new StringBuilder("New consignment was delivered\n");
        message.append(color);
        message.append(" ");
        message.append(flowerType.getName());
        message.append("\t\tcount: ");
        message.append(count);
        message.append("\t\tcost price: ");
        message.append(costPrice);
        throw new LogException(new LogException(message.toString()));
    }

    public void setPrice(Class flowerType, double price, String color) throws PriceException, LogException {
        this.flowerStore.setPrice(flowerType, price, color);
        StringBuilder message = new StringBuilder("New price was established\n");
        message.append(color);
        message.append(" ");
        message.append(flowerType.getName());
        message.append("\t\tprice: ");
        message.append(price);
        throw new LogException(new LogException(message.toString()));
    }

    public FlowerStore getStore() {
        return this.flowerStore;
    }

    public void addToBouquet(Class flowerType, String color) throws PriceException, NoFlowersException {
        this.flowerStore.addToBouquet(this.bouquet, flowerType, color);
    }

    public void sellBouquet() throws LogException {
        for (int i = 0; i < this.bouquet.getCount(); i++) {
            this.income += this.bouquet.getBouquet()[i].getPrice();
        }
        StringBuilder message = new StringBuilder("Next bouquet was sold\n");
        message.append(this.bouquet);
        this.bouquet.clear();
        throw new LogException(new LogException(message.toString()));
    }

    public int howMany(Class flowerType, String color) throws NoFlowersException {
        return this.flowerStore.howMany(flowerType, color);
    }

    public boolean isEmpty() {
        return this.flowerStore.getStacksCount() == 0;
    }

    public Bouquet getBouquet() {
        return this.bouquet;
    }

    public void showAll() {
        System.out.println("=======================================================================");
        System.out.println("Flower store:");
        System.out.println(this.flowerStore);
        System.out.println("-----------------------------------------------------------------------");
        System.out.println("Current bouquet, prepared for sale: ");
        System.out.println(this.bouquet);
        System.out.println("-----------------------------------------------------------------------");
        System.out.println("Total income: " + Interfaces.IConsole.round(this.income, 2));
        System.out.println("Total expences: " + Interfaces.IConsole.round(this.expenses, 2));
        System.out.println("Total profit: " + Interfaces.IConsole.round((this.income - this.expenses), 2));
        System.out.println("=======================================================================");
    }
}