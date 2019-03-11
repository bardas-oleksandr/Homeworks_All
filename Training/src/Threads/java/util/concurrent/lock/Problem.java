package Threads.java.util.concurrent.lock;

import Interfaces.IProblem;

public class Problem implements IProblem {
    @Override
    public void solve() {
        Store store = new Store();
        Producer producer = new Producer(store);
        new Thread(producer).start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Consumer consumer = new Consumer(store);
        Consumer consumer1 = new Consumer(store);

        new Thread(consumer).start();

        new Thread(consumer1).start();
    }
}