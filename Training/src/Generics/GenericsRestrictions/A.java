package Generics.GenericsRestrictions;

public class A<T> {
    T obj;
    //static T x;   //������ ��������� ����������� ����� �������������������� ���� ������ ��� ��� ����� ��������� �����
    // ��� �������� �������. ������������� ���������� ����� ������ ����� ����������� ������ T

    //public static T function_1(){ }   //������ � ����������� ������ ������������ ����������� � ������ �������� ����

    //public static void function_2(T obj){      //������ � ����������� ������ ������������ ����������� � ������ �������� ����
    //}

    public static <V extends Number> V function_2 (V obj){  //� ��� �������� �������� ���� ��������������� � ������ �����
        return obj;
    }
}
