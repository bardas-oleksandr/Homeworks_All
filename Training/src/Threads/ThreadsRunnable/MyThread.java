package Threads.ThreadsRunnable;

public class MyThread implements Runnable {
    private static int count = 0;
    private final int id = count++;

    public void run(){
        try{
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
        finally{
            System.out.println("Will you see it if it's daemon?");  //Если поток имеет статус демона и main уже
            // завершился, то этот блок не будет выполнен
        }
    }
}
