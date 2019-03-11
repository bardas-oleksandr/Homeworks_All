package Problem_01;

class Carnation extends Flower {

    Carnation(String color, double price){
        super(color,price);
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
        return new String("Carnation\t" + super.toString());
    }
}
