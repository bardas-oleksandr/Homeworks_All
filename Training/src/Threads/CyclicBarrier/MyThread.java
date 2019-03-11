package Threads.CyclicBarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class MyThread extends Thread {
    private CyclicBarrier barrier;

    MyThread(CyclicBarrier barrier, String name) {
        super(name);
        this.barrier = barrier;
        super.start();
    }

    @Override
    public void run() {
        System.out.println(super.getName() + " is doing something very important");
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            System.out.println(super.getName() + " is waiting");
            this.barrier.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }

        System.out.println(super.getName() + " continued working"); //Исполнение продолжится только после того как будет
        //выполнен Runnable, запущенный из CyclicBarrier barrier
    }
}
