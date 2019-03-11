package Polymorphism.InheritanceAndReferences;

public class Heir extends Parent {

    private int y;

    public Heir(int x, int y) {
        super(x);
        this.y = y;
    }

    public void heirMethod() {
        System.out.println("y=" + this.y);
    }

    //Статические методы не переопределяются
    //@Override
    public static void staticMethod() {
        System.out.println("Static heir");
    }
}
