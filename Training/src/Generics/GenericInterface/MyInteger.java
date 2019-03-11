package Generics.GenericInterface;

public class MyInteger implements MyComparable<MyInteger> {
    private MyInteger value;

    @Override
    public MyInteger getValue() {
        return this.value;
    }
}
