package Threads.Phaser;

import Interfaces.IProblem;

import java.util.concurrent.Phaser;

public class Problem implements IProblem {
    @Override
    public void solve() {
        Phaser phaser = new Phaser();
        Shared shared = new Shared();
        FirstThread first = new FirstThread(phaser, shared);
        SecondThread second = new SecondThread(phaser, shared);

        try {
            first.join();
            second.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
