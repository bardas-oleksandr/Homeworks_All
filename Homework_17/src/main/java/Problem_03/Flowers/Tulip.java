package Problem_03.Flowers;

public class Tulip extends Flower {

    public Tulip(){
        super();
    }

    public Tulip(String color, double price,  double costPrice){
        super(color, price, costPrice);
    }

    @Override
    public String toString(){
        return new String("Tulip\t\t\t" + super.toString());
    }

    @Override
    public String showForBuyer(){
        return new String("Tulip\t\t\t" + super.showForBuyer());
    }

    @Override
    public String showForProvider(){
        return new String("Tulip\t\t\t" + super.showForProvider());
    }
}
