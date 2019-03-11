package Problem_02;

import Interfaces.*;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Problem implements IProblem {
    @Override
    public void solve() {
        Random rnd = new Random();
        Integer[] arr = {1};
        Set<Integer> set = new Set<>(arr);
        for(int i = 0; i < 1000; i++){
            set.add(rnd.nextInt(10));
        }
        System.out.println("Set: " + set);

        System.out.println("\nremove() checking");
        System.out.println("Removing 1");
        Object value = new Integer(1);
        if(set.remove(value)){
            System.out.println("Value " + value + " was removed");
        }
        System.out.println(set);

        System.out.println("\nRemoving 11");
        value = new Integer(11);
        if(! set.remove(value)){
            System.out.println("Value " + value + " was not removed");
        }
        System.out.println(set);

        System.out.println("\ncontainsAll() checking");
        Integer[] arr1 = {1,2,3};
        List<Integer> list = new LinkedList<>(Arrays.asList(2,3));
        System.out.println("LinkedList" + list);
        System.out.println("Set: " + set);
        if(set.containsAll(list)){
            System.out.println("Set contains all the elements of LinkedList");
        }

        list.add(11);
        System.out.println("\nLinkedList" + list);
        System.out.println("Set: " + set);
        if(! set.containsAll(list)){
            System.out.println("Set doesn't contain all the elements of LinkedList");
        }

        System.out.println("\naddAll() checking");
        Integer[] arr2 = {2,3,4,11,12,13};
        Set<Integer> other = new Set<>(arr2);
        System.out.println("Set # 1: " + set);
        System.out.println("Set # 2: " + other);
        if(set.addAll(other)){
            System.out.println("Two sets union was done");
        }
        System.out.println("Set # 1: " + set);

        System.out.println("\nSituation when union will not be done");
        System.out.println("Set # 1: " + set);
        System.out.println("Set # 2: " + other);
        if(! set.addAll(other)){ //Объедение не изменило множество 1. Множество 2 являяется подмножеством множества 1
            System.out.println("Set # 2 is subset of set # 1");
        }
        System.out.println("Set # 1: " + set);

        System.out.println("\nretainAll() checking");
        Integer[] arr3 = {2,3,4,11,12,13,14,15,16};
        other = new Set<>(arr3);
        System.out.println("Set # 1: " + set);
        System.out.println("Set # 2: " + other);
        if(set.retainAll(other)){
            System.out.println("Retaining of set # 2 elements was done");
        }
        System.out.println("Set # 1: " + set);

        System.out.println("\nSituation when retaining will not be done");
        arr3 = (Integer[])set.toArray();
        other = new Set<>(arr3);
        System.out.println("Set # 1: " + set);
        System.out.println("Set # 2: " + other);
        if(! set.retainAll(other)){ //Пересечение не изменило множество 1, оно являяется подмножеством множества 2
            System.out.println("Set # 1 is subset of set # 2");
        }
        System.out.println("Set # 1: " + set);

        System.out.println("\nremoveAll() checking");
        set.add(20);
        set.add(21);
        other = new Set<>(arr2);
        System.out.println("Set # 1: " + set);
        System.out.println("Set # 2: " + other);
        if(set.removeAll(other)){
            System.out.println("Removing of set # 2 elements was done");
        }
        System.out.println("Set # 1: " + set);
        System.out.println("\nSituation when removing will not be done");
        System.out.println("Set # 1: " + set);
        System.out.println("Set # 2: " + other);
        if(! set.removeAll(other)){ //Вычитание не изменило множество 1, множества 1 и 2 не пересекаются между собой
            System.out.println("Set # 1 and Set # 2 have no common elements");
        }
        System.out.println("Set # 1: " + set);
    }
}
