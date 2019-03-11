package Lambdas.PredefinedFunctionalInterfaces;

import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class MyClass<T> {
    private T value;

    public MyClass(T value){
        this.value = value;
    }

    public T get(){
        return this.value;
    }

    public void doConsumer(Consumer<T> consumer){
        consumer.accept(value);
    }

    public void doPredicate(Predicate<T> predicate){
        if(predicate.test(value)){
            System.out.println("Yes, predicate has worked out");
        }else{
            System.out.println("Sorry, predicate hasn't worked out");
        }
    }

    public void doSupplier(Supplier<T> supplier){
        System.out.println("Supplier: " + supplier.get());
    }
}
