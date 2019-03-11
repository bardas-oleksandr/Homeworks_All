package Threads.Phaser;

public class Shared {
    private int value;

    Shared(){
        this.value = 0;
    }

    int get(){
        return this.value;
    }

    void set(int value){
        this.value = value;
    }
}
