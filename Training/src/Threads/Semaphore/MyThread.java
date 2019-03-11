package Threads.Semaphore;

import java.util.concurrent.Semaphore;

public class MyThread extends Thread {
    private Semaphore semaphore;
    private String name;

    MyThread(Semaphore semaphore, String name){
        super();
        this.semaphore = semaphore;
        this.name = name;
        this.start();
    }

    @Override
    public void run() {
        System.out.println(this.name + " is waiting for permit");

        try {
            semaphore.acquire();
            //semaphore.acquire(2); //ТАК ТОЖЕ МОЖНО. Если мы хотим занять сразу несколько разрешений.
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for(int i = 0; i < 3; i++){
            System.out.println(this.name + " is doing something very important");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println(this.name + " is done");
        semaphore.release();
        //semaphore.release(2);   //ТАК ТОЖЕ МОЖНО. Фактически освобождено одно разрешение, а счетчик уменьшается сразу
        //на два. За счет этого общее количество разрешений семафора по сути увеличивается на одно.
    }
}
