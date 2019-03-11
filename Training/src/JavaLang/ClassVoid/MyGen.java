package JavaLang.ClassVoid;

public class MyGen implements MyInterface<Void> {
    int x;

    public Void doNothing(){
        System.out.println("Yes, it is");
        return null;
    }
}
