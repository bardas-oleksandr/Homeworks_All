package Problem_01;

abstract class Flower {
    private String color;
    private double price;

    Flower(String color, double price){
        this.color = color;
        this.price = price;
    }

    void setColor(String color){
        this.color = color;
    }

    void setPrice(double price){
        this.price = price;
    }

    String getColor(){
        return this.color;
    }

    double getPrice(){
        return this.price;
    }

    abstract void show();

    public String toString(){
        return new String( "Price: " + this.getPrice() + "$\tColor: " + this.getColor());
    }
}
