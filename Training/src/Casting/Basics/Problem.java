package Casting.Basics;

public class Problem implements Interfaces.IProblem {
    public void solve(){
        System.out.println("==========Casting primitive types============");
        short x = 10;
        int y = x;
        x = (short)y;
        int z = 128;
        System.out.println((byte)z);

        System.out.println("==========Casting objects============");
        try{
            Object object = new Father();
            Son son = (Son)object;  //Попытка расширяющего преобразования приведет к исключению ClassCastException
            System.out.println("Father was cast into Son");
        }
        catch(ClassCastException e){
            System.out.println("" + e.getMessage());
        }

        try{
            Object object = new Father();
            Grandfather grandfather = (Grandfather)object;  //Тут все ОК
            System.out.println("Father was cast into Grandfather");
        }
        catch(ClassCastException e){
            System.out.println("" + e.getMessage());
        }

        try{
            Object object = new Father();
            Integer integer = (Integer)object;  //Тут вообще классы не находятся на одной линии наследования. Будет ClassCastException
            System.out.println("Father was cast into Integer");
        }
        catch(ClassCastException e){
            System.out.println("" + e.getMessage());
        }



        System.out.println("==========Working with arrays============");
        try{
            Object mas[] = new Father[3];
            mas[0] = new Son(); //Указатель Father может указывать на объект класса Son
            System.out.println("Son-element was put into array with Father-elements");
        }
        catch(ArrayStoreException e){
            System.out.println("Trying to put into array element of incompatible type: " + e.getMessage());
        }

        try{
            Object mas[] = new Father[3];
            mas[0] = new Grandfather(); //Указатель Father не может указывать на объект класса GrandFather
            System.out.println("Grandfather-element was put into array with Father-elements");
        }
        catch(ArrayStoreException e){
            System.out.println("Trying to put into array element of incompatible type: " + e.getMessage());
        }

        try{
            Object mas[] = new Father[3];
            mas[0] = new Integer(0); //Указатель Father не может указывать на объект класса Integer
            System.out.println("Alien-element was put into array with Father-elements");
        }
        catch(ArrayStoreException e){
            System.out.println("Trying to put into array element of incompatible type: " + e.getMessage());
        }
    }
}
