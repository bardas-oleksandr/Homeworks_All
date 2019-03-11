package Flowers;

public class RoseFlower extends Flower {

    public RoseFlower(){
        super();
    }

    public RoseFlower(String color, double price, double costPrice){
        super(color, price, costPrice);
    }

    @Override
    public String toString(){
        return new String("Rose flower\t\t" + super.toString());
    }

    @Override
    public String showForBuyer(){
        return new String("Rose flower\t\t" + super.showForBuyer());
    }

    @Override
    public String showForProvider(){
        return new String("Rose flower\t\t" + super.showForProvider());
    }
}
