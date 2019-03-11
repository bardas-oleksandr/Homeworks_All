package Annotations.Basics;

@SecondAnnotation(level=5)
public class Pike extends Fish {
    public int length;
    private boolean hungry;

    public Pike(int age){
        super(age);
    }

    @FirstAnnotation(value = 1)
    @SecondAnnotation(level = 2)
    @Override
    public void overridenMethod(int x){
        System.out.println("I am pike!!!");
    }

    @FirstAnnotation(value = 5)
    @SecondAnnotation(level = 6)
    public void publicFromHeir(){
        System.out.println("Do nothing");
    }

    private void privateFromHeir(){
        System.out.println("Do nothing");
    }
}
