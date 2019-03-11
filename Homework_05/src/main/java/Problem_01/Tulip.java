package Problem_01;

public class Tulip extends Flower {

    Tulip(String color, double price){
        super(color,price);
    }

    void show(){
        System.out.println("      * * *     ");
        System.out.println("     ** * **     ");
        System.out.println("      *****      ");
        System.out.println("       ***       ");
        System.out.println("        |        ");
        System.out.println("        |        ");
        System.out.println("        |        ");
        System.out.println("        |        ");
        System.out.println("\n");
    }

    @Override
    public String toString(){
        return new String("Tulip\t\t" + super.toString());
    }
}
