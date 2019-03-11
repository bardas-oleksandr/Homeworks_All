package Flowers;

//import Shop.DataFormat;

public class Carnation extends Flower {

    public Carnation(){
        super();
    }

//    public Carnation(String color, double price, double costPrice, DataFormat format){
//        super(color, price, costPrice, format);
//    }

    public Carnation(String color, double price, double costPrice){
        super(color, price, costPrice);
    }


    @Override
    public String toString(){
        return new String("Carnation\t\t" + super.toString());
    }

    @Override
    public String showForBuyer(){
        return new String("Carnation\t\t" + super.showForBuyer());
    }

    @Override
    public String showForProvider(){
        return new String("Carnation\t\t" + super.showForProvider());
    }
}
