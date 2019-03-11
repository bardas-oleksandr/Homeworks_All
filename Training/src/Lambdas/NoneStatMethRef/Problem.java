package Lambdas.NoneStatMethRef;

import Interfaces.IProblem;

public class Problem implements IProblem {
    @Override
    public void solve() {
        MyClass obj = new MyClass();

        //refMethodOneParam(MyClass::compare, obj);   //None-static method can'r be referenced from a static context
        //������� ������ � ��������� - � ������� ������ �� ����� ����� ��� ������.
        //� ���� �������� ��������� ������, ������� ������ ������������ �� ������ � �������� ���������, ������ ��������
        //� ���� ������ ������� �������� ������ ������ - ������ this.
        //�� ���� �������� �� ������ ����� boolean compare(MyClass other), ������� ���� ����� �������� MyClass other,
        //����� ������ ��� ��� � �������� ��������� ��������� ����� � ����� ����������� (MyClass this, MyCalss other)

        //������� ��������� ����� �������� ����������:
        refMethodTwoParam(MyClass::compare, obj);
        //����� compare ����� ���� ��������, � ����� refMethodTwoParam ��������� ������ �� ����� � ����� �����������
        //�� ��� ��� ��������� ������ ��� ��� �������� ������ �� ������������� ����� ����� ��� ������, ��������� ������
        //�������� � ���� � ������ �������� ������ �������������� ������ - ������ this

        //� ���� ����� �������� ����������
        refMethodOneParam(obj::compare, obj);
        //��� ��� �� �������� �� ��������� �� ������� "refMethodOneParam(MyClass::compare, obj);" - �������� ����� �� ������
        //����� ���������� ������. � ���� �������� � ��������� ������ �� ����������� ����� ������ this.
    }

    public <T> boolean refMethodOneParam(IOneParamMeth<T> method, T other){
        return method.func(other);
    }

    public <T> boolean refMethodTwoParam(ITwoParamMeth<T> method, T other){
        return method.func((T)this, other);
    }
}
