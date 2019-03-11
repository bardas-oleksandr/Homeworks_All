package Casting.InstanceOf;

import Interfaces.IProblem;

public class Problem implements IProblem {
    @Override
    public void solve() {
        B b = new B();
        C c = new C();

        //1 вариант. Приводим объект, на который мы ссылаемся по ссылке класса А (фактически там находится объект класса B) к классу B
        A a = b;
        B b1 = (B)a;
        System.out.println(b1);

        //2 вариант. Приводим объект, на который мы ссылаемся по ссылке класса А (фактически там тоже находится объект класса A) к классу B
        //Так нельзя. Объект суперкласса не имеет всех тех полей, что и объект-наследник
        a = new A();
        if(a instanceof B){
            b1 = (B)a;  //Тут будет ошибка на этапе выполнения
        }else{
            System.out.println("We can't cast");
        }
        System.out.println(b1);

        //3 вариант. Приводим объект, на который мы ссылаемся по ссылке класса А (фактически там находится объект класса С) к классу B
        //Так нельзя. Объекты класса С и В не находятся на одной линии наследования, поэтому могут иметь разные поля
        a = c;
        //b1 = (B)a;  //Тут будет ошибка на этапе выполнения
        System.out.println(b1);

        A a2 = new A();
        B b2 = new B();
        C c2 = new C();
        if(a2 instanceof B){
            System.out.println("You can cast A into B");
        }else{
            System.out.println("You can't cast A into B");
        }
        if(b2 instanceof A){
            System.out.println("You can cast B into A");
        }else{
            System.out.println("You can't cast B into A");
        }
//        if(b2 instanceof C){  //Такое условие даже принимать не хочет,  так как классы B и C не находятся на одной линии наследования
//            System.out.println("You can cast B into C");
//        }else{
//            System.out.println("You can't cast B into C");
//        }
    }
}
