package Annotations.BuildInAnno;

@FunctionalInterface    //Эта аннотация контролирует условие соответствия интерефейса понятию функционального
public interface IFunctional {
    void abstractMethod();  //У Функционального интерфейса должен обязательно быть один и только один абстрактный метод

    default void method_1(){    //Количество методов с реализацией по умолчанию не регламентируется
        System.out.println("Do nothing");
    }

    default void method_2(){    //Количество методов с реализацией по умолчанию не регламентируется
        System.out.println("Do nothing");
    }
}
