package Problem_02;

import java.util.LinkedList;
import java.util.Spliterator;
import java.util.concurrent.Callable;
import java.util.function.Function;

public class Calculator <T> implements Callable {
    private Spliterator<T> spliterator;
    private Function<LinkedList<T>, T> computer;

    public Calculator(Spliterator<T> spliterator, Function<LinkedList<T>, T> computer){
        this.spliterator = spliterator;
        this.computer = computer;
    }

    @Override
    public T call() throws Exception {
        LinkedList<T> source = new LinkedList<>();
        while (spliterator.tryAdvance((el) -> {
            source.add(el);
        })) ;
        if(source.size() > 0){
            return computer.apply(source);
        }else{
            return null;
        }
    }
}
