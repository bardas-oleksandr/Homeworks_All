package Flowers;

abstract public class Flower {
    private String color;
    private double price;
    private double costPrice;

    public Flower(String color, double costPrice){
        this.color = color;
        this.price = 0;
        this.costPrice = costPrice;
    }

    public Flower(String color, double price, double costPrice){
        this.color = color;
        this.price = price;
        this.costPrice = costPrice;
    }

    void setColor(String color){
        this.color = color;
    }

    public void setPrice(double price){ this.price = price; }

    void setCostPrice(double costPrice){
        this.costPrice = costPrice;
    }

    public String getColor(){
        return this.color;
    }

    public double getPrice(){
        return this.price;
    }

    public double getCostPrice(){
        return this.costPrice;
    }

    abstract void show();

    public String toString(){
        return new String( "Price: " + this.getPrice() + "$\t\tCost price: " + this.getCostPrice() + "$\tColor: " + this.getColor());
    }
}
