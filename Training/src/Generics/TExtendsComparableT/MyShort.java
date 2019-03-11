package Generics.TExtendsComparableT;

public class MyShort extends MyInteger {
    void myShortMethod(){
        System.out.println("Method from MyShort");
    }

    @Override
    public void myIntegerMethod(){
        System.out.println("myIntegerMethod from MyShort.class");
    }
}
