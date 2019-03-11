package Threads.ThreadsExecutor;

import Interfaces.IProblem;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Problem implements IProblem {
    @Override
    public void solve() {
        //1 вариант - newCachedThreadPool()
        System.out.println("newCachedThreadPool()");
        ExecutorService exec = Executors.newCachedThreadPool(); //Создает потоки по мере необходимости
        // Если поток отработал, канал для него не удаляется, а может быть использован снова для нового потока
        for(int i = 0; i< 5; i++){
            exec.execute(new Thread(new MyRunnable()));
        }
        exec.shutdown();    //Завершает поток исполнителя. Сам по себе объект Executor работает в отдельном потоке.
        //Вызывать shutdown() обязательно. Иначе программа не завершится
        System.out.println("Yes, I will not wait you"); //Основной поток не будет дожидаться окончания потоков executorа
        try {
            exec.awaitTermination(24, TimeUnit.HOURS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //2 вариант - newFixedThreadPool(5);
        System.out.println("newFixedThreadPool(3)");
        ExecutorService exec1 = Executors.newFixedThreadPool(3);    //Тут количество потоков будет ограничено 3
        for(int i = 0; i< 5; i++){ //Мы обработаем 5 потоков, но все они будут пропущены через пул из 3 готовых потоков
            //Преимущества такого подхода в том что исключен перерасход ресурсов, так мы точно знаем сколько потокков будет создано
            //Пул потоков создается сразу
            exec1.execute(new Thread(new MyRunnable()));
        }
        exec1.shutdown();
        try {
            exec1.awaitTermination(24, TimeUnit.HOURS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //3 вариант - newSingleThreadExecutor();
        try{
            Thread.sleep(1000);
        }
        catch(InterruptedException e){}
        System.out.println("newSingleThreadExecutor()");
        ExecutorService exec2 = Executors.newSingleThreadExecutor();    //Тут будет только 1 поток, все потоки будут пропущены через него
        for(int i = 0; i< 5; i++){
            exec2.execute(new Thread(new MyRunnable()));
        }
        exec2.shutdown();
        try {
            exec2.awaitTermination(24, TimeUnit.HOURS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
