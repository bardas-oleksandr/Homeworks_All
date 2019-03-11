package Threads.CountDownLatch;

import Interfaces.IProblem;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class Problem implements IProblem {
    @Override
    public void solve() {
        //Пример І. Основы
        CountDownLatch countDownLatch = new CountDownLatch(5);

        MyThread thread = new MyThread(countDownLatch);

        System.out.println("Main thread is waiting");
        try {
            countDownLatch.await(10,TimeUnit.SECONDS);  //Предельное ожидание - 10 секунд
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Main thread is no more waiting");

        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //Пример ІІ. Ньюансы работы с методом await(10,TimeUnit.SECONDS)
        countDownLatch = new CountDownLatch(100);

        thread = new MyThread(countDownLatch);

        System.out.println("Main thread is waiting");
        boolean awaitingResults;
        try {
            awaitingResults = countDownLatch.await(4,TimeUnit.SECONDS);  //Предельное ожидание - 4 секунды

            if(awaitingResults == true){
                System.out.println("We have got the final countdown");  //Сработал финальный отсчет
            }else{
                System.out.println("Awaiting time has expired");    //Истекло предельное время ожидания
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
