package Lambdas.Basics;

public class MyClass {
    private int value;

    public MyClass(){
        this.value = 0;
    }

    public MyClass(int value){
        this.value = value;
    }

    public int getIntValue(){
        return this.value;
    }

    public static int getIntRandom(){
        return (int)(100*Math.random());
    }

    public boolean sameValue(MyClass other){
        return this.value == other.value;
    }

    static public <T> boolean genFunc(T first, T second){
        return first.equals(second);
    }
}
