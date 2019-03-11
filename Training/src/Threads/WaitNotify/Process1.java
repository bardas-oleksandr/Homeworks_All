package Threads.WaitNotify;

public class Process1 extends Thread {
    Locker locker;
    Process2 process;

    public Process1(Locker locker) {
        super();
        this.locker = locker;
        this.process = null;
        super.start();
    }

    public void setProcess(Process2 process) {
        this.process = process;
    }

    public void run() {
        try {
            for (int i = 0; i < 5; i++) {
                this.sleep(500);
                this.locker.process2Notified();
            }

            process.interrupt();
            if (process.isInterrupted()) {
                System.out.println("Process 2 is interrupted");
            } else {
                System.out.println("Process 2 is not interrupted");
            }

            this.locker.process2Notified();

            if (process.isInterrupted()) {
                System.out.println("Process 2 is interrupted");
            } else {
                System.out.println("Process 2 is not interrupted");
            }
        } catch (InterruptedException e) {
            System.out.println("Interrupted exception in overridenMethod run() of Processs 1");
        }
    }
}
