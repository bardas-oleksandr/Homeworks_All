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


        System.out.println("==========ArrayIndexOutOf�oundsException============");
        try{
            int mas[] = new int[10];
            mas[100]=1;
        }
        catch(ArrayIndexOutOfBoundsException e){
            System.out.println("Wrong index: " + e.getMessage());
        }

        System.out.println("==========ClassCastException============");
        try{
            //����� ��������� ����������. ���� ������ ������������� ������ (�������� Object), � ���� ������ ��������� ������
            //(�������� Integer), �� ������� ��������� ������.
            //���� ����������� ������������� ������ (Integer) �� ���� ������ (Object) � ������� (�������� � String),
            //��������� �� ��������� � ������ ���� ������ (String �������� �� ��������� � Object)
            //� �� ��������� �� ��������� � ������ ������� (String �� �������� �� ��������� � ������ Integer).
            //��� ����� � ��������� ����������
            //����� �������� ������ � package Cast
            Object number = new Integer(0);
            String string = (String)number;
        }
        catch(ClassCastException e){
            System.out.println("" + e.getMessage());
        }

        System.out.println("==========ArrayStoreException============");
        try{
            //������� ������������� ���������� ������ � package Cast
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
        //IndexOutOf�oundsException
        //NegativeArraySizeException
        //NumberFormatException
        //SecurityException
        //StringindexOutOfBounds
        //�ypeNotPresentException
        //UnsupportedOperationException
        //ClassNotFoundException
        //CloneNotSupportedException
        //IlleqalAccessException
        //InstantiationException
        //InterruptedException
        //NoSuchFieldException
        //NoSuch�ethodException
        //ReflectiveOperationException


    }
}
