package Threads.ThreadsRunnable;

public class NewThread implements Runnable {
    Thread thread;
    private static int count = 0;
    private final int id = count++;

    NewThread(){
        this.thread = new Thread(this);
        this.thread.start();
    }

    public void run(){
        int i = 10;
        while(i-- > 0){
            System.out.println("Thread id: " + this.id + "\ti=" + i);
            try{
                Thread.sleep(500);
            }
            catch(InterruptedException e){
                System.out.println("Thread id: " + this.id + "\tThread was interrupted");
            }
        }
    }
}
