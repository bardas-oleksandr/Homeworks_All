package Exceptions.Inheritance.ClassPack;

import Exceptions.Inheritance.IExample;
import Exceptions.Inheritance.ParentException;

public class ParentClass implements IExample {
    //�� ����� ���������� �� ��������� � ��������� ���������� ��� ������������� ������
    //�� ��� ������ ������ ����� ���������, ���������� ����� ������ ����� "�������" �����������,
    //�� ���� ��������� ������������ ����������
    public void InterfaceThrowsClassDoesnt(){
        System.out.println("Yes, it is");
    }

    //���� � ���������� �� ��������� ��� ����� ������� ����������, �� �� ����� ��������� � ��������� ���������� � ������������� ������
//    public void InterfaceDoesntClassThrows() throws Exception{
//        System.out.println("Oh, it's not");
//    }

    public void BothThrows() throws ParentException{
        System.out.println("Yes, it is");
    }

    public void BothDont(){
        System.out.println("Yes, it is");
    }
}
