package Lambdas.Basics;

import Interfaces.IProblem;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Random;

public class Problem implements IProblem {
    private int value;
    @Override
    public void solve() {
        System.out.println("=========Лямбда-выражения в контексте ссылки на функциональный интерфейс====================");
        FISimple funcInterface;
        funcInterface = ()->10;
        System.out.println("Value = " + funcInterface.getValue());

        funcInterface = ()->{
            Random rnd = new Random();
            return rnd.nextInt(10);
        };
        System.out.println("Value = " + funcInterface.getValue());

        funcInterface = ()-> (int)Math.random()*100;
        System.out.println("Value = " + funcInterface.getValue());

        funcInterface.doNothing();

        NumericTest isEven = (n)-> n%2 == 0;    //Создаем первый экземпляр класса, реализующего интерефейс NumericTest
        int x = 7;
        if(isEven.test(7)){
            System.out.println(x + " is even number");
        }else{
            System.out.println(x + " is odd number");
        }

        NumericTest isPositive = (n)-> n > 0;   //Создаем второй экзмепляр класса, реализующего интерфейс NumericTest
        if(isPositive.test(7)){
            System.out.println(x + " is positive number");
        }else{
            System.out.println(x + " is not positive number");
        }

        NumericTest isOdd = n-> n%2 == 1;   //Если лямбда-выражение имеет один параметр, его необзательно заключать в скобки

        //Обобщенные функциональные интерфейсы
        GenInterface<Integer> first = (n)-> new Integer(n*2);
        System.out.println(first.func(2));

        GenInterface<String> second = (n)-> {
            String result = "";
            for(int i = n.length() - 1; i >= 0; i--){
                result += n.charAt(i);
            }
            return result;
        };
        System.out.println(second.func("Yes, it is"));

        System.out.println("=========Лямбда-выражения в контексте передачи аргументов методов===========================");
        String str1 = "hello";
        System.out.println(str1);
        //1 способ - непосредственно задаем лямбда-выражение при вызове метода
        String str2 = this.function((row)->row.toUpperCase(), str1);  //В метод function мы передаем лямбда-выражение и объект типа String
        System.out.println(str2);
        //2 способ - создаем лямбда-выражение в контексте ссылки на функциональный интерфейс и потом передаем в метод эту ссылку
        StringFunc stringFunc = (row)-> row.toUpperCase();
        String str3 = this.function(stringFunc, str1);
        System.out.println(str3);

        System.out.println("=========Лямбда-выражения и захват переменных===============================================");
        int y = 10;
        NumericTest moreThanY = (n)-> {
            this.value = 10;    //Мы имеем доступ к указателю this класса, в котором создается лямбда-выражение (в данном случае класс Problem)
            //y++;  //Так нельзя. Захваченная локальная переменная не должна изменяться.
            return n > y; //Захвачиваем переменную y. После этого переменная y становится завершенной (final)
        };
        if(moreThanY.test(15)){
            System.out.println("Yes, it is");
        }
        //y=11; //Попытка изменить значение y приводит к тому что выскакивает ошибка в лямбда-выражении: Variable should be final or effectively final

        System.out.println("=========Ссылки на методы===================================================================");
        int res = this.funcMethodRef(MyClass::getIntRandom);    //Передача статического метода по ссылке
        System.out.println(res);
        MyClass obj = new MyClass(7);
        res = this.funcMethodRef(obj::getIntValue);    //Передача нестатического метода по ссылке
        System.out.println(res);
        //res = this.funcMethodRef(obj::getIntRandom);    //Передача нестатического метода по ссылке через объект класса не разрешается.
                                                          // Хотя доступ к статическим методам через объекты класса разрешены

        //Пример передачи ссылки на нестатический метод через имя КЛАССА (то есть для всех объектов класса)
        //Параметр в который надо передать ссылку на метод принадлежит типу MyInterface<T> methodRef
        //Этот фунциональный интерфейс имеет абстрактный метод с двумя параметрами - boolean func(T caller, T other);
        //А фактически в качестве параметра MyInterface<T> methodRef мы передаем ссылку на метод public boolean sameValue(MyClass other),
        // который имеет только ОДИН параметр.
        //Почему же не возникает ошибка? Ведь формально сигнатуры разные.
        //Все дело в том что метод public boolean sameValue(MyClass other) может быть вызван как обычно:
        //this.sameValue(other)
        //а может бть вызван так как вызов реально видит компилятор:
        //this.sameValue(this, other)
        //Так как объекты this и other принадлежат к одному классу, этот как раз вписывается в сигнатуру метода функционального интерфейса:
        //boolean func(T caller, T other)
        boolean answer = this.funcNoneStaticMethodRef(MyClass::sameValue, MyClass.class);
        System.out.println(answer);

        //Ссылки на собственные методы класса и методы суперкласса
        SuperCaller superCaller = new SuperCaller();
        System.out.println(superCaller.show1());
        System.out.println(superCaller.show2());

        System.out.println("=========Ссылки на обобщенные методы========================================================");
        Integer a1 = new Integer(3);
        Integer a2 = new Integer(4);
        answer = this.genMethodRef(MyClass::genFunc, a1, a2);
        if(answer) {
            System.out.println(a1 + "=" + a2);
        }else{
            System.out.println(a1 + "!=" + a2);
        }

        System.out.println("=========Ссылки на конструкторы=============================================================");
        MyClass one = this.constructorRef(MyClass::new);    //Вызываем метод, в качестве аргумента которого выступает ссылка на конструктор
        System.out.println(one.getIntValue());

        CtorRefInterface<?> ctorCaller = MyClass::new;    //Создаем ссылку на конструктор без параметров.
        // Сигнатура конструктора определяется типом функцинального интерфейса
        MyClass two = (MyClass) ctorCaller.constructorCaller();
        System.out.println(two.getIntValue());

        CtorParamRefInterface<?> ctorParamCaller = MyClass::new;    //Создаем ссылку на конструктор с параметрами
        // Сигнатура конструктора определяется типом функцинального интерфейса
        MyClass three = (MyClass) ctorParamCaller.constructorCaller(10);
        System.out.println(three.getIntValue());
    }

    //Метод принимает объект, класс которого имлементирует интерфейсу StringFunc
    //ИЛИ ВМЕСТО ЭТОГО метод может принять лямбда-выражение, соответствующее функциональному интерфейсу StringFunc и объект типа String
    static String function(StringFunc stringFunc, String str){
        return stringFunc.func(str);
    }

    //Метод для демонстрации передачи ссылки на метод в качестве аргумента
    //Вместо объекта, реализующего интерфейс FISimple, можно указать ссылку на метод, который совпадает по сигнатуре с
    //абстрактным методом интерфейса FISimple
    public static int funcMethodRef(FISimple methodRef){
        return methodRef.getValue();
    }

    //Метод для демонстрации передачи в качестве аргумента ссылки на нестатический метод (ссылка через
    // имя класса а не объекта)
    public <T> boolean funcNoneStaticMethodRef(MyInterface<T> methodRef, Class c){
        //Все эти много строк можно даже не анализировать для того чтобы разобраться в том вопросе,
        // который демонстрирует данный пример.
        //Достаточно последней строки.
        //А рефлексия была использована тут только потому что пример в Шилдте немного путает читателя.
        // И создает впечатление что
        //программист обязан передавать объекты класса T в качестве аргументов этого метода.
        //Что очевидно не так.
        T[]arr = (T[])Array.newInstance(c,2);
        Constructor ctr = null;
        try {
            ctr = c.getConstructor(int.class);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        try {
            arr[0] = (T)ctr.newInstance(10);
            arr[1] = (T)ctr.newInstance(9);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        return methodRef.func(arr[0], arr[1]);  //Вызываем метод func интерфейса MyInterface<T>
    }

    public <T> boolean genMethodRef(MyInterface<T> obj, T first, T second){
        return obj.func(first, second);
    }

    public <T> T constructorRef(CtorRefInterface<T> caller){
        return caller.constructorCaller();
    }
}
