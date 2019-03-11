package Lambdas.Basics;

public interface FISimple {
    //Абстрактный метод может быть только один
    int getValue();

    //Методов с реализацией по умолчанию может быть сколько угодно
    default void doNothing(){
        System.out.println("Yes, it is.");
    }
}
