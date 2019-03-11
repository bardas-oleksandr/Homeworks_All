package Generics.GenericsRestrictions;

import Interfaces.IProblem;

public class Problem implements IProblem {
    @Override
    public void solve() {
        System.out.println(A.function_2(10));

        A<Integer> x1 = new A<Integer>();
        //A<Integer>[] x2 = new A<Integer>[2];    //Нельзя создавать массив дженериков под конкретный тип

        A<?> x3[] = new A<?>[2];    //Массив дженериков должен создаваться через мета-символ.
    }
}
