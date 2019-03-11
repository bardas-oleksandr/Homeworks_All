package Problem_02;

import Interfaces.IProblem;
import Interfaces.IRandomizer;

import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.function.Function;

public class Problem implements IProblem {
    public void solve() {
        final int COUNT = 16, MAX = 100;

        //Шаг 1. Создаем коллекию
        LinkedList<Integer> list = new LinkedList<>();
        generateRandom(list, (max) -> new Random().nextInt(max), MAX, COUNT);
        System.out.println("Original list");
        System.out.println(list);

        //Шаг 2. Получаем коллекцию сплитераторов
        LinkedList<Spliterator<Integer>> spliteratorList = spliteratorList(list, 3);

        //Шаг 3. Определяем новую коллекцию на основе коллекции сплитераторов
        System.out.println("Minimum elements:");
        Function<LinkedList<Integer>, Integer> computer = (source) -> {
            int index = 0;
            for (int i = 0; i < source.size(); i++) {
                if (source.get(index) > source.get(i)) {
                    index = i;
                }
            }
            return source.get(index);
        };
        LinkedList<Integer> paramList = computeCollection(spliteratorList, computer);
        System.out.println(paramList);

        System.out.println("Maximum elements:");
        computer = (source) -> {
            int index = 0;
            for (int i = 0; i < source.size(); i++) {
                if (source.get(index) < source.get(i)) {
                    index = i;
                }
            }
            return source.get(index);
        };
        spliteratorList = spliteratorList(list, 3);
        paramList = computeCollection(spliteratorList, computer);
        System.out.println(paramList);

        System.out.println("Average values:");
        computer = (source) -> {
            int sum = 0;
            for (int i = 0; i < source.size(); i++) {
                sum += source.get(i);
            }
            return sum / source.size();
        };
        spliteratorList = spliteratorList(list, 3);
        paramList = computeCollection(spliteratorList, computer);
        System.out.println(paramList);

        //Шаг 4. Сортируем
        Collections.sort(paramList, (x, y) -> x - y);
        System.out.println("Sorted average values");
        System.out.println(paramList);
        System.out.println();

        //Шаг 4. Тестируем все предыдущие операции при работе с коллекцией собственного класса
        LinkedList<Weapon> weaponList = new LinkedList<>();
        weaponList.add(new Weapon("Mauser", 100, 150.99));
        weaponList.add(new Weapon("Beretta", 80, 140.99));
        weaponList.add(new Weapon("Browning", 90, 145.99));
        weaponList.add(new Weapon("Colt", 90, 159.99));
        weaponList.add(new Weapon("Parabellum", 90, 150.99));
        weaponList.add(new Weapon("Astra", 75, 130.99));
        weaponList.add(new Weapon("Glock", 85, 142.99));
        weaponList.add(new Weapon("SIG Sauer", 105, 155.99));
        System.out.println("Original list");
        System.out.println(weaponList);

        Collection<Spliterator<Weapon>> spliteratorWeaponCollection = spliteratorList(weaponList, 2);

        System.out.println("The most powerfull weapons:");
        Function<LinkedList<Weapon>, Weapon> weaponComputer = (source) -> {
            int index = 0;
            for (int i = 0; i < source.size(); i++) {
                if (source.get(index).power() < source.get(i).power()) {
                    index = i;
                }
            }
            return source.get(index);
        };
        LinkedList<Weapon> weaponParamList = computeCollection(spliteratorWeaponCollection, weaponComputer);
        System.out.println(weaponParamList);
        System.out.println("The most powerfull weapons after sorting:");
        Comparator<Weapon> comparator = (x, y) -> y.power() - x.power();
        comparator = comparator.thenComparing(Weapon::price);
        Collections.sort(weaponParamList, comparator);
        System.out.println(weaponParamList);
        System.out.println();
    }

    private <T> void generateRandom(Collection<T> collection, IRandomizer<T> rnd, int max, int count) {
        for (int i = 0; i < count; i++) {
            collection.add(rnd.nextValue(max));
        }
    }

    //Compute new Collection, each element of which is calculated, based on another collection part, determined by spliterator
    private <T> LinkedList<T> computeCollection(Collection<Spliterator<T>> spliteratorList, Function<LinkedList<T>, T> computer) {
        LinkedList<T> list = new LinkedList<>();
        Spliterator<Spliterator<T>> spliterator = spliteratorList.spliterator();
        ExecutorService executor = Executors.newCachedThreadPool();
        ArrayList<Future<T>> results = new ArrayList<>();
        while (spliterator.tryAdvance((spliter) ->results.add(executor.submit(new Calculator<>(spliter, computer)))));
        try {
            for (Future<T> item : results) {
                list.add(item.get());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        executor.shutdown();
        return list;
    }

    //Splits the collection into N parts. Returns LinkedList of corresponding spliterators
    private <T> LinkedList<Spliterator<T>> spliteratorList(Collection<T> collection, int n) {
        if (n >= 0) {
            Spliterator<T> spliterator = collection.spliterator();
            if (collection.size() > 1) {
                spliterator = spliterator.trySplit();
            }
            LinkedList<Spliterator<T>> spliteratorList = new LinkedList<>();
            if (spliterator != null) {
                spliteratorList.add(spliterator);
                while (n > 0) {
                    Spliterator<Spliterator<T>> spliter = spliteratorList.spliterator();
                    LinkedList<Spliterator<T>> nextSpliteratorList = new LinkedList<>();
                    //Метод tryAdvance со всеми элементами коллекции выполняет действие, переданное через лямбда-выражение
                    while (spliter.tryAdvance((el) -> {
                        Spliterator<T> nextSpliter = el.trySplit();
                        nextSpliteratorList.add(el);
                        if (nextSpliter != null) {
                            nextSpliteratorList.add(nextSpliter);
                        }
                    })) ;
                    spliteratorList = nextSpliteratorList;
                    n--;
                }
            }
            return spliteratorList;
        } else {
            throw new IllegalArgumentException("Split parts count can't be less then 1");
        }
    }
}
