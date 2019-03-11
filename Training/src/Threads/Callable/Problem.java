package Threads.Callable;

import Interfaces.IProblem;

import java.util.concurrent.*;

public class Problem implements IProblem {
    @Override
    public void solve() {
        MyCallable<Double> one = new MyCallable<>(10.1);

        ExecutorService executor = Executors.newFixedThreadPool(1);

        Future<Double> result = executor.submit(one);   //Возвращается объект Future, но если процесс Callable
        //еще не завершен, то этот объект будут пустой (без результата).
        //Тут не происходит ожидание. Тут происходит только подписка

        try {
            System.out.println("result: " + result.get());  //Тут происходит ожидание
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        MyCallable<Double> two = new MyCallable<>(9.9);

        result = executor.submit(two);

        try {
            System.out.println("result: " + result.get(2, TimeUnit.SECONDS));//Ждать результат будем 2 секунды
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }catch(TimeoutException e){
            System.out.println("Awaiting time has expired");
        }

        System.out.println("Waiting with TimeUnit");

        TimeUnit unit = TimeUnit.SECONDS;
        MyCallable<Double> three = new MyCallable<>(9.9);
        synchronized (three){
            try {
                unit.timedWait(three, 3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
