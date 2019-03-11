package Collections.Basics;

public class Apple {
    private static int counter;
    private final int y;

    public Apple(){
        this.y = this.counter++;  //Переменную типа final можно инициализировать в конструкторе
    }
}
