package Threads.WaitNotify;

public class Process2 extends Thread {
    Locker locker;
    Process1 process;

    public Process2(Locker locker, Process1 process){
        super();
        this.locker = locker;
        this.process = process;
        super.start();
    }

    public void run(){
        try {
            for (int i = 0; i < 5; i++) {
                this.sleep(500);
                this.locker.process1Notified();
            }
            this.locker.process1Notified();
        } catch (InterruptedException e) {
            System.out.println("Interrupted exception in overridenMethod run() of Processs 2");
        }
    }
}
