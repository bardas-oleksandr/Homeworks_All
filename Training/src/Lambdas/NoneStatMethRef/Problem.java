package Lambdas.NoneStatMethRef;

import Interfaces.IProblem;

public class Problem implements IProblem {
    @Override
    public void solve() {
        MyClass obj = new MyClass();

        //refMethodOneParam(MyClass::compare, obj);   //None-static method can'r be referenced from a static context
        //ѕричина ошибки в следующем - € передаю ссылку на метод через им€ класса.
        //¬ этой ситуации сигнатура метода, который должен передаватьс€ по ссылке в качестве аргумента, должна включать
        //в себ€ первый не€вный аргумент любого метода - ссылку this.
        //“о есть передать по ссылке метод boolean compare(MyClass other), имеющий один €вный аргумент MyClass other,
        //можно только там где в качестве аргумента ожидаетс€ метод с двум€ параметрами (MyClass this, MyCalss other)

        //ѕоэтому следующий вызов €вл€етс€ правильным:
        refMethodTwoParam(MyClass::compare, obj);
        //ћетод compare имеет один аргумент, а метод refMethodTwoParam принимает ссылку на метод с двум€ аргументами
        //Ќо тут все правильно потому что при передаче ссылки на нестатический метод через им€ класса, сигнатура метода
        //включает в себ€ и не€ный аргумент любого нестатического метода - ссылку this

        //» этот вызов €вл€етс€ правильным
        refMethodOneParam(obj::compare, obj);
        //¬се что мы изменили по сравнению со строкой "refMethodOneParam(MyClass::compare, obj);" - передали метод по ссылке
        //через конкретный объект. ¬ этой ситуации в сигнатуре метода не учитываетс€ невна ссылка this.
    }

    public <T> boolean refMethodOneParam(IOneParamMeth<T> method, T other){
        return method.func(other);
    }

    public <T> boolean refMethodTwoParam(ITwoParamMeth<T> method, T other){
        return method.func((T)this, other);
    }
}
