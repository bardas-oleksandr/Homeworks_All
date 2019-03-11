//1. Создать метод, который через Reflection выведет информацию о классе:
// имя класса
// модификаторы доступа
// перечень конструкторов,
// полей,
// методов и
// доступных аннотаций, если есть.

package Problem_01;

import Interfaces.IProblem;

public class Problem implements IProblem {
    public void solve() {
        Informator.inform(Problem_02.Shop.Bouquet.class);

        System.out.println("\n\n");

        Informator.inform(Pike.class);

        System.out.println("\n\n");

        Informator.inform(Fish.class);
    }
}
