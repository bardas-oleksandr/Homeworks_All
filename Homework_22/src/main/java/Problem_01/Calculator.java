package Problem_01;

import java.util.ArrayList;
import java.util.Spliterator;
import java.util.concurrent.Callable;
import java.util.function.Function;

public class Calculator<T, V> implements Callable<V> {
    private Spliterator<T> spliterator; //Указывает на часть набора объектов, над которой надо выполнить расчеты
    private Function<ArrayList<T>, V> computer;  //Описывает способ вычисления показателя на основе набора объектов

    Calculator(Spliterator<T> spliterator, Function<ArrayList<T>, V> computer) {
        this.spliterator = spliterator;
        this.computer = computer;
    }

    @Override
    public V call() throws Exception {
        ArrayList<T> list = new ArrayList<>();
        while(spliterator.tryAdvance((entry)->list.add(entry)));
        return this.computer.apply(list);
    }
}
