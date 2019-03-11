//Пример показывает что метасимвол в качестве аргумента метода не так уж и незаменим

package Generics.MetaSymbol_Revelation;

import Interfaces.IProblem;

public class Problem implements IProblem {
    @Override
    public void solve() {
        GenericClass<Double> obj = new GenericClass<>(10.1);

        doNothing(obj);
        doNothingWithMeta(obj);
    }

    <V> void doNothing(GenericClass<V> x){
        System.out.println("Yes, it is");
        System.out.println(x);
    }

    void doNothingWithMeta(GenericClass<?>  x){ //Этот метод по своей сигнатуре воспринимается  компилятором
        //абсолютно как метод <V> void doNothing(GenericClass<V> x).
        //Если этим методам дать одинаковые названия, компилятор начнет ругаться.
        System.out.println(x);
    }
}


