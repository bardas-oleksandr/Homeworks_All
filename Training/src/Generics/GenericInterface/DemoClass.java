package Generics.GenericInterface;

//<T extends MyComparable<T>> означает что  качестве параметра типа можно передать класс T, или наследника класса Т, который
//имплементирует интерфейс MyComparable<T>, параметризированный этим же классом T.
//Получается что класс должен реализовать интерфейс, параметризированный этим же классом. Ни один наследник класса T не может
//соответствовать этом условию, - так как интерфейс наследника будет параметризирован уже не самим наследником а предком.
//Вывод: в качестве аргумента типа может быть передан только класс (а не наследники), реализующий интерфейс MyComparable<T>,
//параметризированный этим же классом.
public class DemoClass<T extends MyComparable<T>> implements GenInterface<T> {
    private T value;

    public DemoClass(T obj){
        this.value = obj;
    }

    public void show(){
        System.out.println(this.value);
    }
}
