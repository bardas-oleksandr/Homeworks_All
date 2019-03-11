package Problem_01;

import Interfaces.IProblem;

public class Problem implements IProblem {
    public void solve() {
        Integer[] arr1 = {1,2,3,4,5};
        Integer[] arr2 = {6,7,8,9,10,11};
        Stack<Integer> stack1 = new Stack<Integer>(arr1);
        Stack<Integer> stack2 = new Stack<Integer>(arr2);
        System.out.println("Stack #1");
        System.out.println(stack1);
        System.out.println("Stack #2");
        System.out.println(stack2);

        stack1.exchange(stack2);

        System.out.println("Stack #1");
        System.out.println(stack1);
        System.out.println("Stack #2");
        System.out.println(stack2);


    }
}
