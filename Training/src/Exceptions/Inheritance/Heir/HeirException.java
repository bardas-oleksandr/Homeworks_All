package Exceptions.Inheritance.Heir;

import Exceptions.Inheritance.ParentException;

public class HeirException extends ParentException {
    HeirException(int x, int x1){
        //super(x); //���� ����������� �� ���� ������������ ��-�� ��� ������ �������
        super(x, x1);
    }

}
