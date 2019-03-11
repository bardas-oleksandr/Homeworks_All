package Threads.Exchanger;

import java.util.concurrent.Exchanger;

public class FirstThread extends Thread {
    private Exchanger<Double> exchanger;

    FirstThread(Exchanger<Double> exchanger){
        super();
        this.exchanger = exchanger;
        super.start();
    }

    @Override
    public void run(){
        double x = 10.1;
        System.out.println("FirstThread is ready to send this: " + x);
        try {
            x = this.exchanger.exchange(x);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("SecondThread has sent me this: " + x);
    }
}
