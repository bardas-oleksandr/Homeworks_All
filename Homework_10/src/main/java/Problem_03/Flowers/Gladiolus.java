package Problem_03.Flowers;

public class Gladiolus extends Flower {

    public Gladiolus(String color, double costPrice){
        super(color,costPrice);
    }

    void show(){
        System.out.println("        #        ");
        System.out.println("       ###       ");
        System.out.println("   ##   |   ##   ");
        System.out.println("  ####**|**####  ");
        System.out.println("        |        ");
        System.out.println("   ##   |   ##   ");
        System.out.println("  ####**|**####  ");
        System.out.println("        |        ");
        System.out.println("        |        ");
        System.out.println("        |        ");
        System.out.println("\n");
    }

    @Override
    public String toString(){
        return new String("Gladiolus\t\t" + super.toString());
    }
}
