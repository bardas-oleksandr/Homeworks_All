package Annotations.Basics;

@MyAnnotation(message = "Some useless class")
@FirstAnnotation(value=10)
public class Fish {
    public int age;
    private int weight;

    public Fish(int age){
        this.age=age;
    }

    @MyAnnotation(message = "Some useless overridenMethod")
    public void overridenMethod(int x){
        System.out.println("Do nothing");
    }

    private void privateFromParent(){
        System.out.println("Do nothing");
    }

    public void publicFromParent(){
        System.out.println("Do nothing");
    }
}
