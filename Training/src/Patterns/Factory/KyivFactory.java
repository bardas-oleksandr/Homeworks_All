package Patterns.Factory;

public class KyivFactory extends Factory {
    @Override
    public Pizza createPizza() {
        return new KyivCheesePizza();
    }
}
