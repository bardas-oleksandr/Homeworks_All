package Exceptions.Inheritance;

public interface IExample {
    void InterfaceThrowsClassDoesnt() throws Exception;

    //void InterfaceDoesntClassThrows();    //������ ��������� � ParentClass

    void BothThrows() throws ParentException;

    void BothDont();
}
