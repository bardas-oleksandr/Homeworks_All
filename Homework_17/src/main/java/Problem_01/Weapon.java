package Problem_01;

public class Weapon {
    private String name;
    private int power;
    private double price;

    public Weapon(String name, int power, double price){
        this.name = name;
        this.power = power;
        this.price = price;
    }

    public String name(){
        return this.name;
    }

    public int power(){
        return this.power;
    }

    public double price(){
        return this.price;
    }

    @Override
    public String toString(){
        return new String("{" + name + "\tpower:" + power + "\tprice:" + price + "$}");
    }
}
