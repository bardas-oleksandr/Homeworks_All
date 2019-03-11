package Collections.Basics;

import Interfaces.IProblem;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class Problem implements IProblem {
    @Override
    public void solve() {
        List<Integer> listInt = Arrays.asList(16,17,18,19,20);    //Используется список аргументов переменной длины
        listInt.set(1,99);
        //listInt.add(21);  //UnsupportedOperationException. Размер списка не должен меняться
        System.out.println(listInt);

        //1 Способ создания
        Collection<Integer> collection = Arrays.asList(1,2,3,4,5);

        //2 Способ создания. Быстрее первого
        Collection<Integer> collection1 = new ArrayList<>();
        Collections.addAll(collection1, 1,2,3,4,5); //Метод Collections.addAll работает быстрее чем Arrays.asList

        List<Double> listDouble = new ArrayList<Double>();
        Double[] arr = {1.0,2.0,3.0,4.0,5.0};
        Collections.addAll(listDouble, arr);
        System.out.println(listDouble);
        Collections.shuffle(listDouble);
        System.out.println(listDouble);
        Collections.sort(listDouble);
        System.out.println(listDouble);

        {
            List<Double> sub = listDouble.subList(1, 3);
            System.out.println(sub);
            Collections.shuffle(listDouble);
            System.out.println(sub);    //Изменение исходного списка влияют на подсписок и наоборот
            //Collections.sort(listDouble);     //Я теперь не могу сортировать ArrayList, похоже из-за того что есть подсписок
        }
        Collections.sort(listDouble);   //Теперь я снова могу сортировать ArrayList, так как ссылка на подсписок потеряна

        System.out.println("toArray method");
        List<Integer> myList = new ArrayList<>();
        myList.add(1);
        myList.add(2);
        myList.add(3);
        System.out.println("List" + myList);
        Object[] arr1 = myList.toArray();   //Метод возвращает массив НОВЫХ объектов
        arr1[0]=5;
        System.out.println("Array[0]: " + arr1[0]);
        System.out.println("List" + myList);

        myList = new ArrayList<>();
        Collection myCollection = Arrays.asList(arr1);  //При создании коллекции просто копируются ссылки
        System.out.println("Array[0]: " + arr1[0]);
        System.out.println(myCollection);
        arr1[0]=99;
        System.out.println("Array[0]: " + arr1[0]);
        System.out.println(myCollection);


        System.out.println("toArray(T[]) method");
        Set<Integer> e = new TreeSet<Integer>();
        e.add(1);
        e.add(2);
        e.add(3);
        e.add(4);
        Integer[] arr2 = new Integer[5];    //В этом массиве достаточно места для размещения всего множества Set e
        Integer[] arr3 = new Integer[2];    //В этом массиве НЕдостаточно места для размещения всего множества Set e
        Integer[] arr4 = null;

        //Так как в массиве arr2 достаточно места для размещения всего множества Set e, то это множество
        //размещается в массиве arr2 и еще в arr4 (Причем arr2 и arr4 ссылаются на один и тот же массив)
        System.out.println("We have spare room");
        arr4 = e.toArray(arr2);
        System.out.println(arr2[0]);
        System.out.println(arr4[0]);
        arr4[0] = 10;   //Это изменение влияет на оба массива arr2 и arr4
        System.out.println(arr2[0]);
        System.out.println(arr4[0]);

        //Так как в массиве arr3 НЕдостаточно места для размещения всего множества Set e, то это множество
        //размещается только в массиве arr4. Массив arr3 остается пустым.
        System.out.println("We have no spare room");
        arr4 = e.toArray(arr3);
        System.out.println(arr3[0]);
        System.out.println(arr4[0]);
        arr4[0] = 10;   //Это изменение влияет только на массив arr4
        System.out.println(arr3[0]);
        System.out.println(arr4[0]);
        System.out.println(e);

        System.out.println("\nremoveIf method");
        System.out.println(listDouble);
        Predicate<Double> predicate = (x)->{
            return x == 3;
        };
        listDouble.removeIf(predicate);
        System.out.println(listDouble);

        EnumSet<MyEnum> enumSet = EnumSet.allOf(MyEnum.class);
        System.out.println(enumSet);

        System.out.println("listDouble.iterator().forEachRemaining(consumer);");
        System.out.println(listDouble);
        Consumer<Double> consumer = (x)->{
            System.out.println("x=" + x);
            x = x*2;    //Это не повлияет на значение, хранящее в коллекции,
            // так как внутри лямбда-выражения создается новый объект, ссылка на который потом теряется
        };
        listDouble.iterator().forEachRemaining(consumer);
        System.out.println(listDouble);

    }
}
