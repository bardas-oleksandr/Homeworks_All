public class Problem_05 implements IWhatToDo  {
    public void solve() {
        Pizza.Builder_1 builder_1 = new Pizza().new Builder_1();
        Pizza.Builder_2 builder_2 = new Pizza.Builder_2();

        Pizza pizza = new Pizza();
        Pizza.Builder_1 b1 = pizza.create_1();
        Pizza.Builder_2 b2 = pizza.create_2();

        System.out.println("b1.Y=" + b1.Y);
        System.out.println("b2.Y=" + b2.Y);

//        System.out.println("public class Builder_1");
//        System.out.println("Builder.Y=" + builder_1.Y);
//        System.out.println("Pizza.X is inaccessible");
//        System.out.println();
//
//        System.out.println("public static class Builder_2");
//        System.out.println("Builder.Y=" + builder_2.Y);
//        System.out.println("Pizza.X is inaccessible");
//        System.out.println();
//
//        Pizza.Builder_1 builder_11 = new Pizza().new Builder_1();
//        builder_11.Y = 1;
//        System.out.println("public class Builder_1");
//        System.out.println("First object Builder.Y=" + builder_1.Y);
//        System.out.println("Second object Builder.Y=" + builder_11.Y);
//        System.out.println();
//
//        Pizza.Builder_2 builder_21 = new Pizza.Builder_2();
//        builder_21.Y = 1;
//        System.out.println("public static class Builder_2");
//        System.out.println("First object Builder.Y=" + builder_2.Y);
//        System.out.println("Second object Builder.Y=" + builder_21.Y);
//        System.out.println();
//
//        Pizza pizza = new Pizza();
//        pizza.Q1.Y=1;
//        pizza.Q2.Y=2;
//        System.out.println("What is inside pizza");
//        System.out.println("pizza.Q1.Y=" + pizza.Q1.Y);
//        System.out.println("pizza.Q2.Y=" +


    }
}
