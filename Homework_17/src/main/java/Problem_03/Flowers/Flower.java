package Problem_03.Flowers;

import java.io.*;

abstract public class Flower implements Serializable{
    private String color;
    private double price;
    private double costPrice;

    public Flower() {
        this.color = null;
        this.price = 0;
        this.costPrice = 0;
    }

    public Flower(String color, double price, double costPrice){
        this.color = color;
        this.price = price;
        this.costPrice = costPrice;
    }

    void setColor(String color) {
        this.color = color;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    void setCostPrice(double costPrice) {
        this.costPrice = costPrice;
    }

    public String getColor() {
        return this.color;
    }

    public double getPrice() {
        return this.price;
    }

    public double getCostPrice() {
        return this.costPrice;
    }

    @Override
    public String toString() {
        return new String("Price: " + this.getPrice() + "$\t\tCost price: " + this.getCostPrice() + "$\tColor: " + this.getColor());
    }

    public String showForBuyer() {
        return new String("Price: " + this.getPrice() + "$\t\tColor: " + this.getColor());
    }

    public String showForProvider() {
        return new String("Costprice: " + this.getCostPrice() + "$\t\tColor: " + this.getColor());
    }

    private void writeObject(ObjectOutputStream stream) throws IOException{
//        stream.writeChars("\t\t\t\t\t\"color\":" + this.color + ",\n");
//        stream.writeChars("\t\t\t\t\t\"price\":" + this.price + ",\n");
//        stream.writeChars("\t\t\t\t\t\"costPrice\":" + this.costPrice + "\n");
        stream.defaultWriteObject();
    }

    private void readObject(ObjectInputStream stream) throws IOException, ClassNotFoundException{
        stream.defaultReadObject();
    }
}
