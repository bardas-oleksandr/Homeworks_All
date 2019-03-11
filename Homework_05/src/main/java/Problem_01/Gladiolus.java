package Problem_01;

class Gladiolus extends Flower {

    Gladiolus(String color, double price){
        super(color,price);
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
        return new String("Gladiolus\t" + super.toString());
    }
}
