package Problem_02.Flowers;

public class RoseFlower extends Flower {

    public RoseFlower(String color, double costPrice){
        super(color,costPrice);
    }

    void show(){
        System.out.println("        #        ");
        System.out.println("       ###       ");
        System.out.println("   ##   |   ##   ");
        System.out.println("      **|**      ");
        System.out.println("        |        ");
        System.out.println("        |/       ");
        System.out.println("       \\|        ");
        System.out.println("        |/        ");
        System.out.println("       \\|        ");
        System.out.println("        |        ");
        System.out.println("\n");
    }

    @Override
    public String toString(){
        return new String("Rose flower\t\t" + super.toString());
    }
}
