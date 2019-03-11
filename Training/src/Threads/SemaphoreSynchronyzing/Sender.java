package Threads.SemaphoreSynchronyzing;

import java.util.concurrent.Semaphore;

public class Sender extends Thread {
    private Semaphore my;
    private Semaphore his;
    private Store store;

    Sender(Semaphore my, Semaphore his, Store store){
        super();
        this.my = my;
        this.his = his;
        this.store = store;
        super.start();
    }

    @Override
    public void run(){
        int i = 10;
        while(i-- > 0){
            try {
                this.my.acquire();
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("Put: " + this.store.put());

            this.his.release();
        }
    }
}
