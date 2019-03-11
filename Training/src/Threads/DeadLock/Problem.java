package Threads.DeadLock;

import Interfaces.IProblem;

import java.util.concurrent.TimeUnit;

public class Problem implements IProblem {
    @Override
    public void solve() {
        A a = new A();
        B b = new B(a);
        a.setObject(b);
        a.start();
        b.start();

        try {
            a.join(5000);
            b.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
