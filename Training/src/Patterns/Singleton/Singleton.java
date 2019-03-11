package Patterns.Singleton;

public class Singleton {
    private int data;
    private static Singleton instance;  //Нужен доступ типа private и публичный метод-геттер.
    // Возможности изменить значение instance (например присвоить ему значение null) быть не должно

    private Singleton(){}

    static{
        instance = new Singleton();
    }

    public int getData(){
        return this.data;
    }

    public void setData(int data){
        this.data  = data;
    }

    public static Singleton instance(){
        return instance();
    }
}
