package Patterns.Builder;

import Interfaces.IProblem;

public class Problem implements IProblem {
    @Override
    public void solve() {
        Animal lion = Animal.create().isCalled("Lion").hasWeight(150).hasPower(100).getAnimal();

        System.out.println(lion);

        Fish pike = new Fish.Builder().isCalled("Pike").hasLength(50).hasWeight(1).create();
    }
}
