package Generics.GenericInterface;

public class MyDouble implements MyComparable<MyDouble> {
    private MyDouble value;

    @Override
    public MyDouble getValue() {
        return this.value;
    }
}
