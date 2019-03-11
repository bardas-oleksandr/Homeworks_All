package Threads.SemaphoreSynchronyzing;

import Interfaces.IProblem;

import java.util.concurrent.Semaphore;

public class Problem implements IProblem {
    @Override
    public void solve() {
        Semaphore receiverSemaphore = new Semaphore(0);
        Semaphore senderSemaphore = new Semaphore(1);

        Store store = new Store();

        Receiver receiver = new Receiver(receiverSemaphore, senderSemaphore, store);
        Sender sender = new Sender(senderSemaphore, receiverSemaphore, store);

        while(sender.isAlive());
    }
}
