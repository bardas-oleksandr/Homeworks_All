package Classes.VarArgs;

import Interfaces.IProblem;

public class Problem implements IProblem {
    @Override
    public void solve() {
        //� �������� �������� ������ ��������������� ��� ������� ������� � VarArgs
        //������ �� ��������.
        //1. ���� ���� ���������� ��������� ������ ��� ������������� VarArgs, ������ ����� ���������� ������ ����� �����.
        //��� ���� �� ����� ��������� ������� ������ ���������������, ���� ���� ����� � VarArgs ���� �������� ��� ���������
        //2. ������ ��������������� ��������� ������ �����, ����� ��� ���� ����� ���������� �������� ��������� ���� �������,
        // � VarArgs.


        System.out.println("Overloading of VarArgs-methods");
        method(10); //��� ������� ����������������, ���������� �� ������� �� ��, ��� ����� � ���������� �����������
                        // ���������� ��� ���� ��� �� �������
        method(11, 12); //���� ��� ����������������
        method(1,2,3,4,5);  //��� ��������� ����� � ���������� ����������� ����������, ��� ��� ��� ������� ��������

        //method();   //Ambiguous overridenMethod call. ��������� ����� ����� ��������: method(int...x) ��� method(boolean...x)

        //func(1, 2);    //Ambiguous overridenMethod call. ��������� ����� ����� ��������: func(int x, int...y) ��� func(int...y)

        function();
    }

    public void method(int x){
        System.out.println("The only X="+x);
    }

    public void method(int x, int y){
        System.out.println("X="+x);
        System.out.println("Y="+y);
    }

    public void method(int...x){    //������ ������, � ���������� x �� ������ ���������� ��� � �������
        for(int i=0; i<x.length; i++){
            System.out.println("X"+i+"="+x[i]);
        }
    }

    public void method(boolean...x){
        System.out.println("Do nothing");
    }

    public void func(int x, int...y){
        System.out.println("Do nothing");
    }

    public void func(int...y){
        System.out.println("Do nothing");
    }

    public void function(int...args){
        if(args != null){
            System.out.println("Yes, it is");
        }
    }
}
