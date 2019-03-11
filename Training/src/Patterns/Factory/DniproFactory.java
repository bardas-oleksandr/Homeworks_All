package Patterns.Factory;

public class DniproFactory extends Factory {
    @Override
    public Pizza createPizza() {
        return new DniproCheesePizza();
    }
}
