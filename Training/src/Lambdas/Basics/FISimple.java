package Lambdas.Basics;

public interface FISimple {
    //����������� ����� ����� ���� ������ ����
    int getValue();

    //������� � ����������� �� ��������� ����� ���� ������� ������
    default void doNothing(){
        System.out.println("Yes, it is.");
    }
}
