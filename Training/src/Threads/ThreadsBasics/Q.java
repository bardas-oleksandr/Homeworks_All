package Threads.ThreadsBasics;

public class Q {
    int n;
    boolean valueSet = false;

    synchronized void get() {
        while(!valueSet){
            try {
                wait();

            } catch(InterruptedException e) {
                System.out.println("InterruptedException caught");
            }
        }
        try{
            Thread.sleep(500);
        }
        catch(InterruptedException e) {
            System.out.println("InterruptedException caught");
        }
        System.out.println("Got: " + n);
        valueSet = false;
        notify();
    }

    synchronized void put(int n) {
        while(valueSet){
            try {
                wait();
            } catch(InterruptedException e) {
                System.out.println("InterruptedException caught");
            }
        }
        try{
            Thread.sleep(500);
        }
        catch(InterruptedException e) {
            System.out.println("InterruptedException caught");
        }
        this.n = n;
        valueSet = true;
        System.out.println("Put: " + n);
        notify();
    }
}
