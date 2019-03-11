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
            Son son = (Son)object;  //������� ������������ �������������� �������� � ���������� ClassCastException
            System.out.println("Father was cast into Son");
        }
        catch(ClassCastException e){
            System.out.println("" + e.getMessage());
        }

        try{
            Object object = new Father();
            Grandfather grandfather = (Grandfather)object;  //��� ��� ��
            System.out.println("Father was cast into Grandfather");
        }
        catch(ClassCastException e){
            System.out.println("" + e.getMessage());
        }

        try{
            Object object = new Father();
            Integer integer = (Integer)object;  //��� ������ ������ �� ��������� �� ����� ����� ������������. ����� ClassCastException
            System.out.println("Father was cast into Integer");
        }
        catch(ClassCastException e){
            System.out.println("" + e.getMessage());
        }



        System.out.println("==========Working with arrays============");
        try{
            Object mas[] = new Father[3];
            mas[0] = new Son(); //��������� Father ����� ��������� �� ������ ������ Son
            System.out.println("Son-element was put into array with Father-elements");
        }
        catch(ArrayStoreException e){
            System.out.println("Trying to put into array element of incompatible type: " + e.getMessage());
        }

        try{
            Object mas[] = new Father[3];
            mas[0] = new Grandfather(); //��������� Father �� ����� ��������� �� ������ ������ GrandFather
            System.out.println("Grandfather-element was put into array with Father-elements");
        }
        catch(ArrayStoreException e){
            System.out.println("Trying to put into array element of incompatible type: " + e.getMessage());
        }

        try{
            Object mas[] = new Father[3];
            mas[0] = new Integer(0); //��������� Father �� ����� ��������� �� ������ ������ Integer
            System.out.println("Alien-element was put into array with Father-elements");
        }
        catch(ArrayStoreException e){
            System.out.println("Trying to put into array element of incompatible type: " + e.getMessage());
        }
    }
}
