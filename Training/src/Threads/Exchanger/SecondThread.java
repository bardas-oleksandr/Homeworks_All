package Threads.Exchanger;

import java.util.concurrent.Exchanger;

public class SecondThread extends Thread {
    private Exchanger<Double> exchanger;

    SecondThread(Exchanger<Double> exchanger){
        super();
        this.exchanger = exchanger;
        super.start();
    }

    @Override
    public void run(){
        double x = 9.9;
        System.out.println("SecondThread is preparing data for exchanging");

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("SecondThread is ready to send this: " + x);
        try {
            x = this.exchanger.exchange(x);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("FirstThread has sent me this: " + x);
    }
}
