package Lambdas.PredefinedFunctionalInterfaces;

import Interfaces.IProblem;

import java.util.function.Predicate;
import java.util.function.Supplier;

public class Problem implements IProblem {
    @Override
    public void solve() {
        MyClass<Integer> obj = new MyClass<>(5);

        obj.doConsumer((x)->{
            System.out.println("Consumer is working");
            System.out.println("value=" + x);
            x+=2;
            System.out.println("value=" + x);
        });
        System.out.println("obj.get(): " + obj.get());

        Supplier<Integer> supplier = ()->{
            return new Integer(15);
        };
        obj.doSupplier(supplier);

        Predicate<Integer> predicate = (x)->{
            return x.intValue() > 3;
        };
        obj.doPredicate(predicate);

        System.out.println("Two predicates with AND");
        Predicate<Integer> first = (x)->{
            return x>0;
        };
        Predicate<Integer> second = (x)->{
            return x<6;
        };
        obj.doPredicate(first.and(second));
    }
}
