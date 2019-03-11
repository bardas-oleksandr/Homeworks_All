package Exceptions.Basics;

import Interfaces.IProblem;

public class Problem implements IProblem {
    public void solve(){
        System.out.println("==========ArithmeticException============");
        try{
            int x = 0;
            x /=x;
        }
        catch(ArithmeticException e){
            System.out.println("Reason: " + e.getMessage());
        }


        System.out.println("==========ArrayIndexOutOfВoundsException============");
        try{
            int mas[] = new int[10];
            mas[100]=1;
        }
        catch(ArrayIndexOutOfBoundsException e){
            System.out.println("Wrong index: " + e.getMessage());
        }

        System.out.println("==========ClassCastException============");
        try{
            //Когда визникает исключение. Есть ссылка родительского класса (например Object), и есть объект дочернего класса
            //(например Integer), на который указывает ссылка.
            //Если попробовать преобразовать объект (Integer) по этой ссылке (Object) к объекту (например к String),
            //дочернему по отношению к классу типа ссылки (String дочерний по отношению к Object)
            //и не дочернему по отношению к классу объекта (String не дочерний по отношению к классу Integer).
            //Вот тогда и возникает исключение
            //Более подробно смотри в package Cast
            Object number = new Integer(0);
            String string = (String)number;
        }
        catch(ClassCastException e){
            System.out.println("" + e.getMessage());
        }

        System.out.println("==========ArrayStoreException============");
        try{
            //Причина возникновения исключения смотри в package Cast
            Object mas[] = new Integer[3];
            mas[0] = new String();
        }
        catch(ArrayStoreException e){
            System.out.println("Trying to put into Integer array element of incompatible type: " + e.getMessage());
        }

        System.out.println("==========NullPointerException============");
        try{
            Object object = null;
            System.out.println(object.toString());
        }
        catch(NullPointerException e){
            System.out.println("" + e.getMessage());
        }


        //EnumConstantNotPresentException
        //IllegalArgumentException
        //IllegalMonitorStateException
        //IllegalStateException
        //IllegalThreadStateException
        //IllegalThreadException
        //IndexOutOfВoundsException
        //NegativeArraySizeException
        //NumberFormatException
        //SecurityException
        //StringindexOutOfBounds
        //ТypeNotPresentException
        //UnsupportedOperationException
        //ClassNotFoundException
        //CloneNotSupportedException
        //IlleqalAccessException
        //InstantiationException
        //InterruptedException
        //NoSuchFieldException
        //NoSuchМethodException
        //ReflectiveOperationException


    }
}
