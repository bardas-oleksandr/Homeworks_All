package Problem_02;

public enum Singleton {
    Instance(0);

    private int data;

    Singleton(int x){
        this.data = x;
    }

    public void setData(int data){
        this.data = data;
    }

    public int getData(){
        return this.data;
    }
}
