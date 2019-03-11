package Flowers;

public class Gladiolus extends Flower {

    public Gladiolus(String color, double price, double costPrice){
        super(color, price, costPrice);
    }

    @Override
    public String toString(){
        return new String("Gladiolus\t\t" + super.toString());
    }

    @Override
    public String showForBuyer(){
        return new String("Gladiolus\t\t" + super.showForBuyer());
    }

    @Override
    public String showForProvider(){
        return new String("Gladiolus\t\t" + super.showForProvider());
    }
}
