package Generics.WildcardsRevelation;

import java.util.function.Function;
import java.util.function.Predicate;

public class MyStream<T> {
    private T data;

    public MyStream(T data){
        this.data = data;
    }

    public <R> MyStream<R> map(Function<? super T, ? extends R> function){
        return new MyStream<>(function.apply(this.data));
    }

    public <R> MyStream<R> mapWithoutExtendsR(Function<? super T, R> function){
        return new MyStream<>(function.apply(this.data));
    }

    public void filterT(Predicate<T> predicate){
        if(predicate.test(this.data)){
            System.out.println(this.data);
        }
    }

    public void filterSuperT(Predicate<? super T> predicate){
        if(predicate.test(this.data)){
            System.out.println(this.data);
        }
    }


    //Метод не компилируется. Надо помнить и схеме PECS (Provider - extends, Consumer - super)
    //Мы не можем передать объект типа Т в предикат, который может быть параметризирован
    //любым потомком класса Т. Соответственно, в методе test() предиката может потребоваться доступ
    //к тем полям, которых нет у родителя (класс Т), но есть у потомков
//    public void filterExtendsT(Predicate<? extends T> predicate){
//        if(predicate.test(this.data)){
//            System.out.println(this.data);
//        }
//    }

    //Метод не компилируется. Нельзя передать объект типа Т в предикат, который может быть параметризирован
    //абсолютно любым классом. Если окажется что предикат параметризирован классом, который не приводится к
    //классу Т, то будет уже поздно...
//    public void filter(Predicate<?> predicate){
//        if(predicate.test(this.data)){
//            System.out.println(this.data);
//        }
//    }
}
