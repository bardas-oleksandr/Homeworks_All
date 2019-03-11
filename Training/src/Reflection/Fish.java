package Reflection;

public class Fish {
    private int length;

    public Fish(){
        this.length = 0;
    }

    public Fish(int length){
        this.length = length;
    }

    public int howLong(){
        return this.length;
    }

    public void setLength(int length){
        this.length = length;
    }
}
