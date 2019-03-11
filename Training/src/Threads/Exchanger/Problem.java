package Threads.Exchanger;

import Interfaces.IProblem;

import java.util.concurrent.Exchanger;

public class Problem implements IProblem {
    @Override
    public void solve() {
        Exchanger<Double> exchanger = new Exchanger<>();

        FirstThread first = new FirstThread(exchanger);
        SecondThread second = new SecondThread(exchanger);

        try {
            first.join();
            second.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
