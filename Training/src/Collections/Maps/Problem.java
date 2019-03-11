package Collections.Maps;

import Interfaces.IProblem;
import javafx.collections.transformation.SortedList;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;

public class Problem implements IProblem {
    @Override
    public void solve() {
        Map<Integer, String> map = new LinkedHashMap<>();
        map.put(1, "Pike");
        map.put(2, "Perch");
        map.put(3, "Zander");
        map.put(4, "Wales catfish");
        map.put(5, "Eel");

        Set<Map.Entry<Integer, String>> set = map.entrySet();
        Set<Integer> keySet = map.keySet();
        Collection<String> valuesSet = map.values();
        System.out.println("map.entrySet(): " + set);
        System.out.println("map.keySet(): " + keySet);
        System.out.println("map.values(): " + valuesSet);

        Iterator<Map.Entry<Integer, String>> iterator = set.iterator();
        Map.Entry<Integer, String> mapEntry = iterator.next();
        System.out.println(mapEntry.getValue());

        System.out.println("compute() method testing");
        System.out.println(map);
        BiFunction<Integer, String, String> biFunction = (number, oldString)->{
            if(number%2 == 1){
                return new String(oldString.toUpperCase());
            }else{
                return new String(oldString.toLowerCase());
            }
        };
        String string = map.compute(1, biFunction);
        System.out.println(map);

        System.out.println("computeIfAbsent() method testing");
        Function<Integer,String> calcFunction = (key)->{
            if(key%2 == 0){
                return new String("Tench");
            }else{
                return new String("Salmon");
            }
        };
        map.computeIfAbsent(6,calcFunction);
        System.out.println(map);

        System.out.println("forEach() method testing");
        BiConsumer<Integer, String> biConsumer = (key, value)->{
            if(key%2 == 0){
                System.out.println(value.toUpperCase());
            }else{
                System.out.println(value.toLowerCase());
            }
        };
        map.forEach(biConsumer);

        //Создадим анонимный класс компаратора
        Comparator<Integer> comparator = new Comparator<Integer>() {
            @Override
            //Сначала четные, потом нечетные
            public int compare(Integer o1, Integer o2) {
                if (o1 % 2 == 1) {
                    if (o2 % 2 == 1) {
                        return o1 - o2;
                    } else {
                        return o1;
                    }
                } else {
                    if (o2 % 2 == 1) {
                        return -o1;
                    } else {
                        return o1 - o2;
                    }
                }
            }
        };
        Map<Integer, String> bigMap = new TreeMap<>(comparator);
        bigMap.put(0, "Crocodile");
        bigMap.put(1, "Lion");
        bigMap.put(2, "Tyger");
        bigMap.put(3, "Bear");
        bigMap.put(4, "Wolf");
        bigMap.put(5, "Wale");
        bigMap.put(6, "Python");
        System.out.println(bigMap);

        //Компаратор можно также написать через лямбда-выражение
        Comparator<Integer> lambdaComparator = (o1, o2) -> {
            if (o1 % 2 == 1) {
                if (o2 % 2 == 1) {
                    return o1 - o2;
                } else {
                    return o1;
                }
            } else {
                if (o2 % 2 == 1) {
                    return -o1;
                } else {
                    return o1 - o2;
                }
            }
        };
        //Вызовем метод reversed() для сортировки в обратном порядке
        bigMap = new TreeMap<>(lambdaComparator.reversed());
        bigMap.put(0, "Crocodile");
        bigMap.put(1, "Lion");
        bigMap.put(2, "Tyger");
        bigMap.put(3, "Bear");
        bigMap.put(4, "Wolf");
        bigMap.put(5, "Wale");
        bigMap.put(6, "Python");
        System.out.println(bigMap);

        //Первый компаратор сравнивает по длине строки
        Comparator<String> stringComparator = (o1, o2)->{
            return o1.length() - o2.length();
        };
        Function<String, Integer> function = (o)->{
            return o.codePointAt(0);
        };
        //Второй компаратор будет сравнивать по номеру первого символа
        Comparator<String> complexComparotor = stringComparator.thenComparing(function);
        //В это дерево мы можем добавить только те строки, которые отличают хотя бы
        //по одному из компараторов двойного компаратора
        TreeSet<String> treeSet = new TreeSet<String>(complexComparotor);
        treeSet.add("Hello");
        treeSet.add("world");
        treeSet.add("Yes");
        treeSet.add("OK");
        treeSet.add("No");
        treeSet.add("Ooооооооо");
        treeSet.add("ooооооооо");
        treeSet.add("oфффффффф");   //Добавлено не будет потому что уе есть элемент такой длины и с такой первой буквой
        System.out.println(treeSet);

        //Можно использовать собственный класс компаратора
        treeSet = new TreeSet<String>(new MyComparator<>());
        treeSet.add("Hello");
        treeSet.add("world");
        treeSet.add("Yes");
        treeSet.add("OK");
        treeSet.add("No");
        treeSet.add("Ooооооооо");
        treeSet.add("ooооооооо");
        System.out.println(treeSet);
    }
}
