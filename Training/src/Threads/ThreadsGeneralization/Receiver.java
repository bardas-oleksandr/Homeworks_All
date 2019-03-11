package Threads.ThreadsGeneralization;

public class Receiver extends Thread {
    private Freight freight;
    private int delta;
    private Locker locker;
    private boolean suspended;

    public Receiver(Freight freight, int delta, Locker locker) {
        super();
        this.freight = freight;
        this.delta = delta;
        this.locker = locker;
        this.suspended = false;
        super.start();
    }

    void receive() {
        freight.modify(-delta);
        System.out.println("Total count: " + freight.getCount());
    }

    @Override
    public void run() {
        while (true) {
            while (this.locker.isSending()) {
                this.locker.senderNotify();
            }

            waitingWhileSuspended();

            receive();
            this.locker.senderNotify();
        }
    }

    synchronized void waitingWhileSuspended(){
        while(this.suspended){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    synchronized void pause(){
        this.suspended=true;
    }

    synchronized void goAhead(){
        this.suspended=false;
        notify();
    }
}
