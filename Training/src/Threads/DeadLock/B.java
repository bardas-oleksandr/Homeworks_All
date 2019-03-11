package Threads.DeadLock;

public class B extends Thread {
    private A object;
    private int value;

    B(A object){
        super();
        this.object = object;
        this.value = 0;
    }

    @Override
    public void run() {
        this.callA();
    }

    synchronized void callA(){
        System.out.println("Object B is on monitor");

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Trying to get monitor for object A");
        System.out.println(object.value());
    }

    synchronized int value(){
        return this.value;
    }
}
