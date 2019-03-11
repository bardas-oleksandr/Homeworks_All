package Threads.CyclicBarrier;

import Interfaces.IProblem;

import java.util.concurrent.CyclicBarrier;

public class Problem implements IProblem {
    @Override
    public void solve() {
        CyclicBarrier barrier = new CyclicBarrier(3,new MyRunnable());

        MyThread A = new MyThread(barrier,"A");
        MyThread B = new MyThread(barrier,"B");
        MyThread C = new MyThread(barrier,"C");

        try {
            A.join();
            B.join();
            C.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
