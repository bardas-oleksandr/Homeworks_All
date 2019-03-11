package Problem_01;

import Interfaces.IProblem;
import Interfaces.IRandomizer;

import java.util.*;
import java.util.function.Function;

public class Problem implements IProblem {
    public void solve() {
        final int COUNT = 30, MAX = 100;

        //Шаг 1. Создаем коллекию и делим ее на части
        LinkedList<Integer> list = new LinkedList<>();
        generateRandom(list, (max) -> new Random().nextInt(max), MAX, COUNT);
        System.out.println("Original list");
        System.out.println(list);

        Collection<LinkedList<Integer>> splitCollection = split2N(list, 3);
        System.out.println("List, split into 8 parts");
        show(splitCollection);
        System.out.println();

        //Шаг 2. Определяем показатели для каждой из разделенных частей
        System.out.println("Minimum elements:");
        Function<LinkedList<Integer>, Integer> computer = (source) -> {
            int index = 0;
            for (int i = 0; i < source.size(); i++) {
                if (source.get(index) > source.get(i)) {
                    index = i;
                }
            }
            return new Integer(source.get(index));
        };
        LinkedList<Integer> paramList = computeCollection(splitCollection, computer);
        System.out.println(paramList);

        System.out.println("Maximum elements:");
        computer = (source) -> {
            int index = 0;
            for (int i = 0; i < source.size(); i++) {
                if (source.get(index) < source.get(i)) {
                    index = i;
                }
            }
            return new Integer(source.get(index));
        };
        paramList = computeCollection(splitCollection, computer);
        System.out.println(paramList);

        System.out.println("Average values:");
        computer = (source) -> {
            int sum = 0;
            for (int i = 0; i < source.size(); i++) {
                sum += source.get(i);
            }
            return new Integer(sum / source.size());
        };
        paramList = computeCollection(splitCollection, computer);
        System.out.println(paramList);

        //Шаг 3. Сортируем
        Collections.sort(paramList, (x, y) -> {
            return x - y;
        });
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

        Collection<LinkedList<Weapon>> splitWeaponCollection = split2N(weaponList, 2);
        System.out.println("Split list");
        show(splitWeaponCollection);

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
        LinkedList<Weapon> weaponParamList = computeCollection(splitWeaponCollection, weaponComputer);
        System.out.println(weaponParamList);
        System.out.println("The most powerfull weapons after sorting:");
        Comparator<Weapon> comparator = (x, y) -> {
            return y.power() - x.power();
        };
        comparator = comparator.thenComparing((x) -> x.price());
        Collections.sort(weaponParamList, comparator);
        System.out.println(weaponParamList);
        System.out.println();

        //Шаг 5. Тестируем разделение коллекции на части для разных ситуаций
        splitCollection = split2N(list, 1);
        System.out.println("List, split into 2 parts");
        show(splitCollection);
        System.out.println();

        splitCollection = split2N(list, 0);
        System.out.println("List, split into 1 part");
        show(splitCollection);
        System.out.println();

        list = new LinkedList<>();
        generateRandom(list, (max) -> new Random().nextInt(max), MAX, 1);
        System.out.println("Original list");
        System.out.println(list);
        System.out.println();

        splitCollection = split2N(list, 2);
        System.out.println("List, split into 2 parts");
        show(splitCollection);
        System.out.println();
    }

    public <T> void generateRandom(Collection<T> collection, IRandomizer<T> rnd, int max, int count) {
        for (int i = 0; i < count; i++) {
            collection.add(rnd.nextValue(max));
        }
    }

    //Splits the collection into 2^N parts.(N=1 - 2 parts; N=2 - 4 parts)
    public <T> LinkedList<LinkedList<T>> split2N(Collection<T> collection, int n) {
        if (n >= 0) {
            LinkedList<LinkedList<T>> list = new LinkedList<>();    //eventual result
            Spliterator<Spliterator<T>> spliterator = spliteratorList(collection, n).spliterator();
            while (spliterator.tryAdvance((el) -> {
                LinkedList<T> source = new LinkedList<>();
                while (el.tryAdvance((el1) -> {
                    source.add(el1);
                })) ;
                if (source.size() > 0) {
                    list.add(source);
                }
            })) ;
            return list;
        } else {
            throw new IllegalArgumentException("Split parts count can't be less then 1");
        }
    }

    //Splits collection into N parts. Returns LinkedList of corresponding spliterators
    private <T> LinkedList<Spliterator<T>> spliteratorList(Collection<T> collection, int n) {
        if (n >= 0) {
            Spliterator<T> spliterator = collection.spliterator();
            //Сплитератор устроен странно
            //Если размер коллекции равен 1, то первый же вызов метода trySplit() возвращает null
            //Что вполне логично, так как коллекция такой длины не делится. А дальше не очень логично
            //Если размер коллекции больше 1, то первый вызов сплитератора не делит коллекцию.
            //Вернее делит ее на 1 часть. Тогда непонятно почему нельзя было сделать тоже самое
            //тогда когда размер коллекции равен 1.
            //В результате этой нелогичности получается что для разделения коллекции из одного элемента
            //на отдельные части по одному элементу, метод trySplit() вообще не надо вызывать
            //А для разделения коллекции и двух частей - метод надо вызвать два раза.
            //Следующее условие учитывает эту нелогичность
            if (collection.size() > 1) {
                spliterator = spliterator.trySplit();
            }
            LinkedList<Spliterator<T>> spliteratorList = new LinkedList<>();    //Это то что мы будем возвращать из метода
            if (spliterator != null) {
                spliteratorList.add(spliterator);   //Добавляем в коллекцию сплитераторов сплитератор, указующий на всю
                //разбиваемую коллекицю Collection<T> collection
                while (n > 0) {   //За каждый цикл мы будем сплитить (расщеплять) все сплитераторы из коллекции и результат такого
                    //расщепления будем записывать для возможности следуюзего расщепления
                    LinkedList<Spliterator<T>> nextSpliteratorList = new LinkedList<>();

                    //І вариант. Через Iterator. Наверное более понятен
                    Iterator<Spliterator<T>> iterator = spliteratorList.iterator();
                    iterator.forEachRemaining((el) -> {
                        Spliterator<T> nextSpliter = el.trySplit();
                        nextSpliteratorList.add(el);
                        if(nextSpliter!=null){
                            nextSpliteratorList.add(nextSpliter);
                        }
                    });

                    //ІІ вариант. Через Spliterator. Spliterator<Spliterator<T>> - это портит карму
//                    Spliterator<Spliterator<T>> spliter = spliteratorList.spliterator();
//                    while (spliter.tryAdvance((el) -> {
//                        Spliterator<T> nextSpliter = el.trySplit();
//                        nextSpliteratorList.add(el);
//                        if (nextSpliter != null) {
//                            nextSpliteratorList.add(nextSpliter);
//                        }
//                    })) ;

                    spliteratorList = nextSpliteratorList;
                    n--;
                }
            }
            return spliteratorList;
        } else {
            throw new IllegalArgumentException("Split parts count can't be less then 1");
        }
    }

    //Method computes key for every collection member and returns new collection, containing
    //obtained keys
    public <T, V> LinkedList<V> computeCollection(Collection<T> source, Function<T, V> computer) {
        LinkedList<V> list = new LinkedList<>();
        Iterator<T> iterator = source.iterator();
        while (iterator.hasNext()) {
            list.add(computer.apply(iterator.next()));
        }
        return list;
    }

    public <T> void show(Collection<LinkedList<T>> collection) {
        Iterator<LinkedList<T>> iterator = collection.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
