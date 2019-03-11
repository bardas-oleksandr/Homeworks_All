package Problem_01;

class RoseFlower extends Flower {

    RoseFlower(String color, double price){
        super(color,price);
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
        return new String("Rose flower\t" + super.toString());
    }
}
