package Exceptions.Inheritance;

public interface IExample {
    void InterfaceThrowsClassDoesnt() throws Exception;

    //void InterfaceDoesntClassThrows();    //Смотри пояснения в ParentClass

    void BothThrows() throws ParentException;

    void BothDont();
}
