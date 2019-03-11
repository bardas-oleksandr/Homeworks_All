package Classes.AccessLevels.SomePackage;

public class Parent {
    public int x;   //Доступна всем
    protected int y;    //Доступна всем внутри package и всем наследникам
    int q;  //Доступна всем внутри package
    private int z;  //Доступна только внутри класса
}
