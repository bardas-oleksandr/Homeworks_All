package Threads.ThreadsGeneralization;

public class Locker {
    private boolean sending;

    Locker(){
        sending = true;
    }

    synchronized void senderNotify(){
        this.sending = true;
        notify();
        try {
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    synchronized void receiverNotify(){
        this.sending = false;
        notify();
        try {
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    boolean isSending(){
        return this.sending;
    }
}
