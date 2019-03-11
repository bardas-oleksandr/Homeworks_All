package Generics.TExtendsComparableT;

public interface MyInterface <T extends Comparable<T>> {
    T min();
    T max();
}
