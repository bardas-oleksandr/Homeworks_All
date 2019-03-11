package Patterns.Factory;

import Interfaces.IProblem;

public class Problem implements IProblem {
    @Override
    public void solve() {
        Shop celentano = new Shop(new DniproFactory());
        Pizza pizza = celentano.create();
        System.out.println(pizza);

        celentano.changeFactory(new KyivFactory());
        pizza = celentano.create();
        System.out.println(pizza);
    }
}
