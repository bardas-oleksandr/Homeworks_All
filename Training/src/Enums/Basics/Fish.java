package Enums.Basics;

//enum ����� ���������������� ���������
//�� �� ����� ����������� �����-�� �����
//(������ ��� ����� ���������� ���������� enum ��� ����������� �� Enum
public enum Fish implements MyInterface {
    Pike("I'm pike"), Perch("I'm perch"), Tench("I'm tench"), Zander("I'm zander"), Catfish("I'm catfish");

    private String say; //���� ����� ������ ����� �� ���, ��� � �����

    //1.� enum ����� ������� ������ ���� �����������. ������� ������� ������������ - private.
    //������, �� �� ����� ��� ��������.
    //2.���� ����������� ����� �������� ������ �� ����� Fish.Pike()
    //3. private is redundant
    private Fish(String message){
        this.say = message;
    }

    public String say(){
        return this.say;
    }
}
