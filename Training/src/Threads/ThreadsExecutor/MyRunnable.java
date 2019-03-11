package Threads.ThreadsExecutor;

public class MyRunnable implements Runnable  {
    private static int count = 0;
    private final int id = ++count;

    public void run(){
        for(int i=0; i<5; i++){
            System.out.println("id: " + id + "\t i=" + i);
        }
    }
}
