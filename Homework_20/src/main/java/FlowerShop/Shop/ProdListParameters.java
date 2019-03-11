package FlowerShop.Shop;

import java.io.Serializable;
import java.util.Observable;
import java.util.Observer;

public class ProdListParameters extends Observable implements Serializable  {
    private double price;
    private String name;
    private String color;

    public ProdListParameters(){
        this.name = "None";
        this.color = "None";
        this.price = 0;
    }

    public ProdListParameters(String name, String color, double price, Observer observer){
        this.name = name;
        this.color = color;
        this.price = price;
        this.addObserver(observer);
        this.setChanged();
        this.notifyObservers();
    }

    void setName(String name) {
        this.name = name;
    }

    void setColor(String color) {
        this.color = color;
    }

    void setPrice(double price) {
        this.price = price;
        this.setChanged();
        this.notifyObservers();
    }

    public String name() {
        return this.name;
    }

    public String color() {
        return this.color;
    }

    public double price() {
        return this.price;
    }
}
