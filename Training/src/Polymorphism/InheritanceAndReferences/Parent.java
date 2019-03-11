package Polymorphism.InheritanceAndReferences;

public class Parent {
    private int x;

    public Parent(int x){
        this.x = x;
    }

    public void parentMethod(){
        System.out.println("x=" + this.x);
    }

    public static void staticMethod(){
        System.out.println("Static parent");
    }
}
