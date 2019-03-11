package ua.levelup.XML.LookupMethodInjection;

public abstract class AbstractCasanova implements Guy {
    private Damsel damsel;

    //Метод Damsel getDamsel(); не имплементирован, поэтому класс является абстрактным
    //Но даже несмотря на это мы можем создать экземпляр бина такого класса
    //Для этого мы используем Lookup method injection

    @Override
    public void findDamsel() {
        System.out.println("Hello, lady. What is your name?");
        getDamsel().speak();
    }
}
