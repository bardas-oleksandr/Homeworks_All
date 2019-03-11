package Reflection;

public class MyGeneric<T extends Number> {
    private T value;

    public MyGeneric(T obj){
        this.value = obj;
    }

    public MyGeneric(){
        this.value = (T)(new Integer(10));
    }

    public String getType(){
        return value.getClass().getName();
    }
}
