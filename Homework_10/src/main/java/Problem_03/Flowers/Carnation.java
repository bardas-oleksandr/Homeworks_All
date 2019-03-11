package Problem_03.Flowers;

public class Carnation extends Flower {

    public Carnation(String color, double costPrice){
        super(color,costPrice);
    }

    void show(){
        System.out.println("        #        ");
        System.out.println("   *   ###   *   ");
        System.out.println("   ##***|***##   ");
        System.out.println("     ***|***     ");
        System.out.println("        |        ");
        System.out.println("        | *      ");
        System.out.println("      * |/       ");
        System.out.println("       \\| **     ");
        System.out.println("     ** |/        ");
        System.out.println("       \\|        ");
        System.out.println("        |        ");
        System.out.println("\n");
    }

    @Override
    public String toString(){
        return new String("Carnation\t\t" + super.toString());
    }
}
