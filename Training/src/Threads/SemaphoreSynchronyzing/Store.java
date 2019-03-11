package Threads.SemaphoreSynchronyzing;

public class Store {
    private int count;

    Store(){
        this.count = 0;
    }

    int put(){
        return ++this.count;
    }

    int get(){
        return --this.count;
    }
}
