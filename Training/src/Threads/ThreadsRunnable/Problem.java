package Threads.ThreadsRunnable;

import Interfaces.IProblem;

public class Problem implements IProblem {
    @Override
    public void solve() {
//1 способ работы с Runnable - объект класса Thread находится внутри объекта класса, имплементирующего Runnable
        NewThread t1 = new NewThread();
        NewThread t2 = new NewThread();
        try{
            t1.thread.join();
            t2.thread.join();
        }
        catch(InterruptedException e){
            System.out.println("Thread was interrupted");
        }

        //2 способ. В классе, имплементирующем Runnable, нет объекта класса thread
        Thread t3 = new Thread(new MyThread());
        Thread t4 = new Thread(new MyThread());
        t3.setDaemon(true);
        t4.setDaemon(true);
        t3.start();
        t4.start();
        try{
            t3.join();
            t4.join();
        }
        catch(InterruptedException e){
            System.out.println("Thread was interrupted");
        }
    }
}
