package Generics.TExtendsComparableT;

public class MyGenClass <T extends Comparable<T>> implements MyInterface<T> {
    @Override
    public T min() {
        return null;
    }

    @Override
    public T max() {
        return null;
    }
}
