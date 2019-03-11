package Classes.VarArgs;

import Interfaces.IProblem;

public class Problem implements IProblem {
    @Override
    public void solve() {
        //¬ примерах показаны ошибки неоднозначности при вызовах методов с VarArgs
        //¬ыводы по примерам.
        //1. ≈сли есть подход€ща€ сигнатура метода без использовани€ VarArgs, всегда будет вызыватьс€ именно такой метод.
        //ѕри этом не будет возникать никаких ошибок неоднозначности, даже если метод с VarArgs тоже подходит под сигнатуру
        //2. ќшибка неоднозначности возникает только тогда, когда под один набор аргументов подходит сигнатура двух методов,
        // с VarArgs.


        System.out.println("Overloading of VarArgs-methods");
        method(10); //Ќет никакой неопределенности, компил€тор не смотрит на то, что метод с переменным количеством
                        // аргументов тут тоже мог бы подойти
        method(11, 12); //“оже нет неопределенности
        method(1,2,3,4,5);  //“ут вызоветс€ метод с переменным количеством аргументов, так как нет другого варианта

        //method();   //Ambiguous overridenMethod call. Ќепон€тно какой метод вызывать: method(int...x) или method(boolean...x)

        //func(1, 2);    //Ambiguous overridenMethod call. Ќепон€тно какой метод вызывать: func(int x, int...y) или func(int...y)

        function();
    }

    public void method(int x){
        System.out.println("The only X="+x);
    }

    public void method(int x, int y){
        System.out.println("X="+x);
        System.out.println("Y="+y);
    }

    public void method(int...x){    //¬нутри метода, к переменной x мы должны обращатьс€ как к массиву
        for(int i=0; i<x.length; i++){
            System.out.println("X"+i+"="+x[i]);
        }
    }

    public void method(boolean...x){
        System.out.println("Do nothing");
    }

    public void func(int x, int...y){
        System.out.println("Do nothing");
    }

    public void func(int...y){
        System.out.println("Do nothing");
    }

    public void function(int...args){
        if(args != null){
            System.out.println("Yes, it is");
        }
    }
}
