package Classes.InnerAndNestedClasses;

import Interfaces.IProblem;

public class Problem implements IProblem {
    @Override
    public void solve() {
        App.staticMethod(); //РАЗОБРАТЬСЯ С ЭТИМ. СОГЛАСНО СТАТЬЕ https://juja.com.ua/java/inner-and-nested-classes/
        //тут должна вылетать ошибка из-за того что метод локального класса пытается получить доступ к локальной
        //не финальной переменной из метода staticMethod. ПРИЧЕМ пример автора статьи действительно выдает ошибку.
        //Когда же тоже самое делал я, ошибки не было

        Outer outer = new Outer();
        outer.doNothing();

        //Nested Inner classes
        Outer.Inner x1 = new Outer().new Inner();
        x1.show();

        //Static Nested classes or Member of outer class
        Outer.StaticInner x2 = new Outer.StaticInner();
        x2.show();
    }
}
