package Threads.WaitNotify;

public class Locker {
    private boolean processing;

    public Locker() {
        this.processing = false;
    }

    public synchronized void process1Notified() {
        try {
            while (this.processing) {
                wait();
            }
            System.out.println("Process 1 was notified");
            this.processing = true;
            notify();
        } catch (InterruptedException e) {
            System.out.println("InterruptedException caught");
        }
    }

    public synchronized void process2Notified() {
        try {
            while (!this.processing) {
                wait();
            }
            System.out.println("Process 2 was notified");
            this.processing = false;
            notify();
        } catch (InterruptedException e) {
            System.out.println("InterruptedException caught");
        }
    }
}
