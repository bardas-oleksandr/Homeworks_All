package Threads.CyclicBarrier;

public class MyRunnable implements Runnable {
    @Override
    public void run() {
        System.out.println("CyclicBarrier has started new runnable process");

        for (int i = 0; i < 10; i++) {
            System.out.println("MyRunnable is working: " + i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
