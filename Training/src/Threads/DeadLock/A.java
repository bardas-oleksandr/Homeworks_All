package Threads.DeadLock;

public class A extends Thread {
    private B object;
    private int value;

    A(){
        super();
        this.value = 0;
    }

    void setObject(B object){
        this.object = object;
    }

    @Override
    public void run() {
        this.callB();
    }

    synchronized void callB(){
        System.out.println("Object A is on monitor");

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Trying to get monitor for object B");
        System.out.println(object.value());
    }

    synchronized int value(){
        return this.value;
    }
}
