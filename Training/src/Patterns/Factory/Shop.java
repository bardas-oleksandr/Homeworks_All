package Patterns.Factory;

public class Shop {
    private Factory factory;

    public Shop(Factory factory){
        this.factory = factory;
    }

    public Pizza create(){
        return this.factory.createPizza();
    }

    public void changeFactory(Factory factory){
        this.factory = factory;
    }
}
