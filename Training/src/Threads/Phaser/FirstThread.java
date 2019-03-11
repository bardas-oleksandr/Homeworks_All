package Threads.Phaser;

import java.util.concurrent.Phaser;

public class FirstThread extends Thread {
    private Phaser phaser;
    private Shared shared;

    FirstThread(Phaser phaser, Shared shared) {
        super();
        this.shared = shared;
        this.phaser = phaser;
        this.phaser.register();
        super.start();
    }

    @Override
    public void run() {
        try {
            int x;
            System.out.println(this.getClass().getName() + " is performing very complicated calculations");

            Thread.sleep(2000);

            this.shared.set(10);
            this.phaser.arrive();   //Это сигнал для объекта Phaser, что наш поток выполнил свою часть работы
            System.out.println(this.getClass().getName() + ": I have done my part of the work and I will not wait");

            Thread.sleep(500);

            System.out.println(this.getClass().getName() + " is waiting for results of another thread");
            this.phaser.arriveAndAwaitAdvance();    //Тут мы будем ждать до тех пор, пока все учасники процесса не выполнят
            // аналогичный этап расчета
            System.out.println(this.getClass().getName() + ": Another thread has calculated this value:" + this.shared.get());

            this.phaser.arriveAndDeregister();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
