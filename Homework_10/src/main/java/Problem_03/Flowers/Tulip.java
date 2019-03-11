package Problem_03.Flowers;

public class Tulip extends Flower {

    public Tulip(String color, double costPrice){
        super(color,costPrice);
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
        return new String("Tulip\t\t\t" + super.toString());
    }
}
