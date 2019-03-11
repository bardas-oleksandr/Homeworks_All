package Patterns.SingletonEnum;

public enum SingletonEnum {
    INSTANCE;

    private int data;

    SingletonEnum(){
        this.data = 0;
    }

    public int getData(){
        return this.data;
    }

    public void setData(int data){
        this.data = data;
    }
}
