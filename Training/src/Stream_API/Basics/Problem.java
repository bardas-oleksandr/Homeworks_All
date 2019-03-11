package Stream_API.Basics;

import Interfaces.IProblem;

import java.util.*;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Problem implements IProblem {
    @Override
    public void solve() {
        //Пример 1.
        {
            ArrayList<Integer> list = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                list.add(i);
            }
            Stream<Integer> stream = list.stream();
            Optional<Integer> min = stream.min(Integer::compare);
            min.ifPresent(integer -> System.out.println("Min: " + integer));
        }

        //Пример 2. Свой компаратор
        {
            ArrayList<Fish> fishList = new ArrayList<>();
            fishList.add(new Fish("Pike", 3, 6));
            fishList.add(new Fish("Zander", 2, 5));
            fishList.add(new Fish("Catfish", 4, 6));
            fishList.add(new Fish("Perch", 1, 10));

            Stream<Fish> fishStream = fishList.stream();
            System.out.println("============All the fish=============");
            fishStream.forEach(System.out::println);
            fishStream = fishList.stream();
            Comparator<Fish> comparator = (first, second) -> first.weight() - second.weight();
            Optional<Fish> theBiggest = fishStream.max(comparator);
            theBiggest.ifPresent(fish -> System.out.println("The biggest fish: " + fish));
        }

        //Пример 3. Методы sorted(), unordered() и forEachOrdered()
        {
            ArrayList<Integer> list = new ArrayList<>();
            Random rnd = new Random();
            for (int i = 0; i < 10; i++) {
                list.add(rnd.nextInt(10));
            }
            System.out.println("================LIST============================================");
            list.forEach(System.out::print);
            Stream<Integer> stream = list.parallelStream();
            System.out.println("\n================ALL THE NUMBERS IN PARALLEL STREAM===============");
            System.out.println("================Without any processing========================");
            //Тут мы получим поток с хаотичным порядком элементов (так как поток обрабатывается параллельно)
            stream.forEach(System.out::print);

            stream = list.parallelStream();
            System.out.println("\n================unordered()===================================");
            //Тут мы получим поток с хаотичным порядком элементов. Метод unordered() снимает обязательство
            //следить за порядком элементов при параллельной обработке.
            stream.unordered().forEach(System.out::print);

            stream = list.parallelStream();
            System.out.println("\n================sorted()======================================");
            //I. Метод sorted() отсортирует объекты в потоке (не в источнике!!!) согласно методу
            //compareTo(). Естественно, что сортируемые объекты должны имплементировать Comparable
            //II. Так как в данном случае обработка выполняется в режиме parallelStream, то на экран
            //поток будет выведен вовсе не отсортированным.
            stream.sorted().forEach(System.out::print);

            stream = list.parallelStream();
            System.out.println("\n================forEachOrdered()==============================");
            //Метод forEachOrdered() выводит элементы потока в той последовательности, в которой
            //они находятся в источнике. Этот метод стоит применять только в случае parallelStream.
            //В случае sequential - в этом просто нет смысла.
            stream.forEachOrdered(System.out::print);

            stream = list.parallelStream();
            System.out.println("\n================sorted().forEachOrdered()=====================");
            //Объекты сначала будут отсортированы методом sorted()
            //Затем метод forEachOrdered обеспечит отсортированный порядок вывода на экран при
            //параллельной обработке
            stream.sorted().forEachOrdered(System.out::print);

            stream = list.parallelStream();
            System.out.println("\n================unordered().forEachOrdered()==================");
            //Метод unordered() предполагает использование того порядка элементов, в котором они
            //находились в источнике.
            //Метод forEachOrdered проследит за тем чтобы такой порядок был соблюден при параллельной обработке.
            stream.unordered().forEachOrdered(System.out::print);
            System.out.println();
        }


        //Пример 4. Три вида reduce
        {
            ArrayList<Integer> list = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                list.add(10 - i);
            }

            //Первый вид reduce. Просто задаем как нужно сводить два элемента
            //При этом виде reduce мы не можем изменить тип возвращаемого значения
            //Если Stream<Integer>, то получим Optional<Integer>
            //Для примера находим сумму всех элементов
            Stream<Integer> stream = list.parallelStream();
            Optional<Integer> summ = stream.reduce((a, b) -> a + b);
            summ.ifPresent(integer -> System.out.println("Summ = " + integer));

            //Второй вид reduce. Дополнительно задаем начальное значение
            //При этому виде reduce мы не можем изменить тип возвращаемого значения
            //Если Stream<Integer>, то получим Integer
            //Для примера найдем произведение квадратов всех элементов, которые больше 5
            stream = list.stream(); //Если тут поставить parallelStream() то при сведении результатов
            //полученных в отдельных потоках, из этих результатов будет снова извлечен корень,
            //что даст неверный результат. Поэтому при parallelStream() надо использовать
            //третью форму reduce
            Integer multiply = stream.reduce(1, (a, b) -> {
                if (b > 5) {
                    return a * (int) Math.pow(b, 2);
                } else {
                    return a;
                }
            });
            System.out.println("Multiply result: " + multiply);

            //Третий вид reduce. Дополнительно задаем как сводить вместе результаты,
            //полученные в разных потоках
            //В этом виде reduce мы можем изменить тип возвращаемого значения
            //Например если был Stream<Integer>, можем получить Double
            //Для примера найдем сумму всех квадратов
            stream = list.parallelStream();
            BiFunction<Double, Integer, Double> reduceMethod = (a, b) -> {
                if (b > 5) {
                    return a * Math.sqrt(b);
                } else {
                    return a;
                }
            };
            BinaryOperator<Double> uniteMethod = (a, b) -> a + b;
            Double summSqrt = stream.reduce(1.0, reduceMethod, uniteMethod);
            System.out.println("Summ sqrt: " + summSqrt);
        }

        //Пример 5. Отображение с помощью map
        {
            System.out.println("=======================MAP==========================");
            ArrayList<Fish> fishList = new ArrayList<>();
            fishList.add(new Fish("Pike", 3, 6));
            fishList.add(new Fish("Zander", 2, 5));
            fishList.add(new Fish("Pike", 4, 8));
            fishList.add(new Fish("Catfish", 4, 6));
            fishList.add(new Fish("Perch", 1, 10));
            fishList.add(new Fish("Pike", 1, 3));

            Stream<Fish> fishStream = fishList.stream();
            //Выведем в поток веса только щук
            Stream<Integer> pikesWeightsStream = fishStream
                    .filter((fish) -> fish.name().equals("Pike"))
                    .map(Fish::weight);
            //Суммируем вес щук
            Optional<Integer> totalPikesWeight = pikesWeightsStream.reduce((a, b) -> a + b);
            totalPikesWeight.ifPresent(integer -> System.out.println("Total pikes weight: " + integer));
        }

        //Пример 6. Использование Collector
        {
            ArrayList<Fish> fishList = new ArrayList<>();
            fishList.add(new Fish("Pike", 3, 6));
            fishList.add(new Fish("Zander", 2, 5));
            fishList.add(new Fish("Pike", 4, 8));
            fishList.add(new Fish("Catfish", 4, 6));
            fishList.add(new Fish("Perch", 1, 10));
            fishList.add(new Fish("Pike", 1, 3));

            Stream<Fish> fishStream = fishList.stream();
            //Выведем новую коллекцию вес всех рыб
            List<Integer> weights = fishStream
                    .map((a) -> a.weight())
                    .collect(Collectors.toList());
            System.out.println("==============ALL THE WEIGHTS==============");
            weights.forEach(System.out::println);
        }

        //Пример 7. Создания своего коллектора
        {
            ArrayList<Fish> fishList = new ArrayList<>();
            fishList.add(new Fish("Pike", 3, 6));
            fishList.add(new Fish("Zander", 2, 5));
            fishList.add(new Fish("Pike", 4, 8));
            fishList.add(new Fish("Catfish", 4, 6));
            fishList.add(new Fish("Perch", 1, 10));
            fishList.add(new Fish("Pike", 1, 3));

            Stream<Fish> fishStream = fishList.stream();
            //Выведем новую коллекцию вес всех рыб
            List<Integer> weights = fishStream
                    .map((a) -> a.weight()).collect(ArrayList<Integer>::new,
                            (list,item)->list.add(item),
                            (list1, list2)->list1.addAll(list2));
            System.out.println("==============ALL THE WEIGHTS==============");
            weights.forEach(System.out::println);
        }

        //Пример 8. Метод peek
        {
            //Этот пример показывает следующее:
            //В потоке для каждого элемента выполняется метод peek,
            //в котором над каждым элементом выполняется операция удаления из источника
            //Несмотря на удаление, следующая операция forEach(System.out::println)
            //успешно выводит все элементы на экран.
            //После завершения всех операций строки, источник не будет содержать ни одного элемента
            System.out.println("============Method peek()======================");
            List<String> list = new ArrayList<>();
            list.add("1");
            list.add("2");
            list.stream().sorted().peek(list::remove).forEach(System.out::println);
            System.out.println(list.size());
        }

        //Пример 9. Метод iterate
        {
            System.out.println("============Method iterate()======================");
            IntStream.iterate(1, i->i*2).limit(5).forEach(System.out::println);
        }

        //Пример 9. Метод distinct()
        {
            System.out.println("============Method distinct()======================");
            ArrayList<Integer> list = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                list.add(i);
                list.add(i);
            }
            System.out.println("===================Stream before distinction================");
            list.stream().forEach((x)-> System.out.print(x + "\t"));
            System.out.println("\n===================Stream after distinction=================");
            list.stream().distinct().forEach((x)-> System.out.print(x + "\t"));
            System.out.println();
        }

        //Пример 10. Методы allMatch(), anyMatch(), noneMatch()
        {
            System.out.println("============allMatch(), anyMatch(), noneMatch()======================");
            ArrayList<Integer> list = new ArrayList<>();
            for (int i = 1; i < 10; i++) {
                list.add(i);
                list.add(i);
            }

            if(list.stream().anyMatch((x)->x%2==1)){
                System.out.println("There are at least one odd element");
            }

            if(list.stream().allMatch((x)->x>0)){
                System.out.println("All the the elements are positive");
            }

            if(list.stream().noneMatch((x)->x>100)){
                System.out.println("There are no elements bigger then 100");
            }
        }

        //Пример 11. Метод of()
        {
            Fish pike = new Fish("Pike", 2,5);
            //Статический метод of() возвращает Stream, состоящий из единственного элемента
            Stream.of(pike).forEach(System.out::println);
        }

        //Пример 12. Метод flatMap()
        {
            ArrayList<Man> manList = new ArrayList<>();
            List<Integer> list = new ArrayList<>();
            list.add(1);
            list.add(2);
            list.add(3);
            list.add(4);
            manList.add(new Man(list));
            list.clear();
            list.add(5);
            list.add(6);
            list.add(7);
            list.add(8);
            manList.add(new Man(list));

            //Разница между методами map и flatMap в том что flatMap принимает лямбда выражение, возвращающее Stream
            //из которого потом ивлекаются все элементы и соединаются в один поток.

            //Тут стрим листов
            Stream<List<Integer>> listStream = manList.stream().map(Man::getList);
            //Тут стрим интеджеров, полученных из листов
            Stream<Integer> integerStream = manList.stream().flatMap((man)->man.getList().stream());

        }
    }
}
