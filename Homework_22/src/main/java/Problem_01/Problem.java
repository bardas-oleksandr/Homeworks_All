package Problem_01;

import Interfaces.IProblem;

import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.function.Function;

public class Problem implements IProblem {
    @Override
    public void solve() {
        final int SERIES_COUNT = 10;
        final int RANGE = 6;
        long[] average = new long[RANGE];
        int[] threadsCounts = new int[RANGE];
        double[] answers = new double[RANGE];

        //Создадим массив людей со случайными параметрами
        ArrayList<Human> humanList = new ArrayList<>();
        Random rnd = new Random();
        for (int i = 0; i < 10; i++) {
            double heigth = 1.5 + rnd.nextDouble() * 0.5;
            double weight = 50 + rnd.nextDouble() * 70;
            int years = 18 + rnd.nextInt(50);
            Human human = new Human(heigth, weight, years);
            humanList.add(human);
            System.out.println(human);
        }

        //Цикл по количеству серий экспериментов
        for(int experiment = 0; experiment < SERIES_COUNT; experiment++){
            //Цикл по исследуемому диапазону количества потоков
            for (int n = 0; n < RANGE; n++) {
                long time = -System.nanoTime();
                //I этап. Вычисляем общую сумму для отдельно взятых частей коллекции
                //Разобъем коллекцию на (2 в степени n) частей
                int threadsCount = (int) Math.pow(2, n);
                ArrayList<Spliterator<Human>> spliteratorList = split2N(humanList, n);

                //Определим способ вычисления результата для каждой части коллекции (общий вес всех людей)
                Function<ArrayList<Human>, Double> computer = (list) -> {
                    double res = 0;
                    for(Human person: list){
                        res+= person.weight();
                    }
                    return res;
                };

                ExecutorService executor = Executors.newFixedThreadPool(threadsCount);  //Пул потоков фиксированного размера
                ArrayList<Future<Double>> results = new ArrayList<>();  //Сюда запишем результаты
                Spliterator<Spliterator<Human>> spliterator = spliteratorList.spliterator();    //Сплитератор для перебора
                //всех сплитераторов, которые делят коллекцию на части, каждая из которых предназначена для отдельного потока

                //Для всех частей коллекции создаем объект класса Calculator, запускаем его на исполнение в Executor
                //и помещаем результат в ArrayList<Future<Double>> results
                while (spliterator.tryAdvance((split) -> {
                    Calculator<Human, Double> calculator = new Calculator<>(split, computer);
                    results.add(executor.submit(calculator));
                })) ;

                //ІІ этап. Вычисляем общую сумму для всей коллекции
                try {
                    double totalWeight = 0;
                    for (Future<Double> entry : results) {
                        totalWeight += entry.get();
                    }
                    time += System.nanoTime();
                    if(experiment != 0){    //Почему-то во время первого опыта первый расчет выполняется за несравненимо
                        //большее время независимо от количества потоков
                        average[n] += time;
                        threadsCounts[n] = threadsCount;
                        answers[n] = totalWeight;
                    }
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
            }
        }

        for(int i = 0 ; i < RANGE; i++){
            System.out.println(new Formatter().format("Total weight: %7.2f kg\tThreads: %2d\tTime: %10d",
                    answers[i], threadsCounts[i], average[i]));
        }
    }

    private <T> ArrayList<Spliterator<T>> split2N(ArrayList<T> list, int n) {
        if (n >= 0) {
            ArrayList<Spliterator<T>> spliteratorList = new ArrayList<>();
            Spliterator<T> spliterator = list.spliterator();
            spliteratorList.add(spliterator);
            while (n > 0) {
                ArrayList<Spliterator<T>> nextSpliteratorList = new ArrayList<>();
                for (Spliterator<T> entry : spliteratorList) {
                    Spliterator<T> second = entry.trySplit();
                    nextSpliteratorList.add(entry);
                    if (second != null) {
                        nextSpliteratorList.add(second);
                    }
                }
                spliteratorList = nextSpliteratorList;
                n--;
            }
            return spliteratorList;
        } else {
            throw new IllegalArgumentException("Split parts count can't be less then 1");
        }
    }
}