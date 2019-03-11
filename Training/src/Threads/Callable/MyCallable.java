package Threads.Callable;

import java.util.concurrent.Callable;

public class MyCallable<V> implements Callable<V> {
    private V obj;

    MyCallable(V obj){
        this.obj = obj;
    }

    @Override
    public V call() throws Exception {
        System.out.println("Doing something very important");

        Thread.sleep(5000);

        return this.obj;
    }
}
