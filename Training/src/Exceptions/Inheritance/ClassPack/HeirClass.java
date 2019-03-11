package Exceptions.Inheritance.ClassPack;

import Exceptions.Inheritance.Heir.HeirException;

public class HeirClass extends ParentClass {

    //���� ����� � ������������ ������ ������ ���������� ParentException, ���������� ���� ������������ ��� ���������� HeirException
    //����� ������� ���������� �������, ��� ���������
    @Override
    public void BothThrows() throws HeirException {
        System.out.println("Yes, it is");
    }

    //���� ����� � ������������ ������ ������ ���������� ParentException, ���������� ���� �������� ��� ���������� Exception
    //����� ������� ���������� ����������, ��� �����������
//    @Override
//    public void BothThrows() throws Exception{
//        System.out.println("Yes, it is");
//    }
}
