package Exceptions.Inheritance.ClassPack;

import Exceptions.Inheritance.IExample;
import Exceptions.Inheritance.ParentException;

public class ParentClass implements IExample {
    //Мы можем отказаться от объяления о проброске исключения при имплементации метода
    //Но при вызове метода через интерфейс, компилятор будет видеть метод "глазами" интерефейса,
    //то есть потребует обрабатывать исключение
    public void InterfaceThrowsClassDoesnt(){
        System.out.println("Yes, it is");
    }

    //Если в интерфейсе не объявлено что метод бросает исключение, мы не можем объявлять о проброске исключения в имплементации метода
//    public void InterfaceDoesntClassThrows() throws Exception{
//        System.out.println("Oh, it's not");
//    }

    public void BothThrows() throws ParentException{
        System.out.println("Yes, it is");
    }

    public void BothDont(){
        System.out.println("Yes, it is");
    }
}
