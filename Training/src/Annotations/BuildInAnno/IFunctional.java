package Annotations.BuildInAnno;

@FunctionalInterface    //��� ��������� ������������ ������� ������������ ����������� ������� ���������������
public interface IFunctional {
    void abstractMethod();  //� ��������������� ���������� ������ ����������� ���� ���� � ������ ���� ����������� �����

    default void method_1(){    //���������� ������� � ����������� �� ��������� �� ����������������
        System.out.println("Do nothing");
    }

    default void method_2(){    //���������� ������� � ����������� �� ��������� �� ����������������
        System.out.println("Do nothing");
    }
}
