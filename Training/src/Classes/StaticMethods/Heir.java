package Classes.StaticMethods;

public class Heir extends Parent {
    private int x;

    //@Override //Такая аннтоация приведет к ошибке на этапе компиляции. Причина - компилятор таким образом предостерегает нас от ошибки
                //Ведь статические методы не могут перегружаться и быть полиморфными
    public static String speak(){
        return "I am your son";
    }

    public void staticCaller(){
        this.speak();   //Статический метод можно вызвать через this
    }
}
