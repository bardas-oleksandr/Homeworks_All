package Threads.ThreadsGeneralization;

public class Freight {
    private int count;

    void modify(int delta){
        this.count += delta;
    }

    int getCount(){
        return this.count;
    }
}
