package Classes.AbstractClasses;

public abstract class AbstractFather {  //Абстрактный класс. Такой как же как и обычный, но его экземпляр создать нельзя
    private int x;

    public AbstractFather(int x){
        this.x = x;
    }

    public int getX(){
        return this.x;
    }

    public abstract void doNothing();   //Абстрактный метод. Его недо обязательно переопределить в наследнике
}
