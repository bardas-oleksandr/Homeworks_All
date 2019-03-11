package Threads.CountDownLatch;

import java.util.concurrent.CountDownLatch;

public class MyThread extends Thread {
    private CountDownLatch countDownLatch;

    MyThread(CountDownLatch countDownLatch){
        super();
        super.setDaemon(true);
        this.countDownLatch = countDownLatch;
        super.start();
    }

    @Override
    public void run(){
        int i=0;
        while(i++ < 10){
            System.out.println("countDown: " + this.countDownLatch.getCount());

            this.countDownLatch.countDown();

            if(this.countDownLatch.getCount() == 0){
                System.out.println("MyThread is still working");
            }

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
