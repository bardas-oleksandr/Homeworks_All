package Casting.InstanceOf;

import Interfaces.IProblem;

public class Problem implements IProblem {
    @Override
    public void solve() {
        B b = new B();
        C c = new C();

        //1 �������. �������� ������, �� ������� �� ��������� �� ������ ������ � (���������� ��� ��������� ������ ������ B) � ������ B
        A a = b;
        B b1 = (B)a;
        System.out.println(b1);

        //2 �������. �������� ������, �� ������� �� ��������� �� ������ ������ � (���������� ��� ���� ��������� ������ ������ A) � ������ B
        //��� ������. ������ ����������� �� ����� ���� ��� �����, ��� � ������-���������
        a = new A();
        if(a instanceof B){
            b1 = (B)a;  //��� ����� ������ �� ����� ����������
        }else{
            System.out.println("We can't cast");
        }
        System.out.println(b1);

        //3 �������. �������� ������, �� ������� �� ��������� �� ������ ������ � (���������� ��� ��������� ������ ������ �) � ������ B
        //��� ������. ������� ������ � � � �� ��������� �� ����� ����� ������������, ������� ����� ����� ������ ����
        a = c;
        //b1 = (B)a;  //��� ����� ������ �� ����� ����������
        System.out.println(b1);

        A a2 = new A();
        B b2 = new B();
        C c2 = new C();
        if(a2 instanceof B){
            System.out.println("You can cast A into B");
        }else{
            System.out.println("You can't cast A into B");
        }
        if(b2 instanceof A){
            System.out.println("You can cast B into A");
        }else{
            System.out.println("You can't cast B into A");
        }
//        if(b2 instanceof C){  //����� ������� ���� ��������� �� �����,  ��� ��� ������ B � C �� ��������� �� ����� ����� ������������
//            System.out.println("You can cast B into C");
//        }else{
//            System.out.println("You can't cast B into C");
//        }
    }
}
