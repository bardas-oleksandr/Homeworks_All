package Collections.Spliterators;

import Interfaces.IProblem;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Spliterator;

public class Problem implements IProblem {
    @Override
    public void solve() {
        //Example shows some crap that happens in case of using spliterator for Collections and LinkedLists with length>1
        //Case I. Spliterator for ArrayList
        System.out.println("ArrayList");
        ArrayList<Integer> arrayList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            arrayList.add(i);
        }
        Spliterator<Integer> first = arrayList.spliterator();
        Spliterator<?> otherFirst = first.trySplit();   //Collection is split into two parts. As it have being expected
        while(first.tryAdvance(el-> System.out.print(el + "\t")));

        //Case II. Spliterator for LinkedList
        System.out.println("\n\nLinkedList");
        LinkedList<Integer> linkedList = new LinkedList<>();
        for (int i = 0; i < 10; i++) {
            linkedList.add(i);
        }
        Spliterator<Integer> second = linkedList.spliterator();
        Spliterator<?> otherSecond = second.trySplit(); //Collection is split into one part. And all of collection now
        //is asigned to Spliterator<?> otherSecond
        while(second.tryAdvance(el-> System.out.print(el + "\t")));

        //Case III. Spliterator for Collection
        System.out.println("\n\nCollection");
        Collection<Integer> collection = new LinkedList<>();
        for (int i = 0; i < 10; i++) {
            collection.add(i);
        }
        Spliterator<Integer> third = linkedList.spliterator();
        Spliterator<?> otherThird = third.trySplit();
        while(third.tryAdvance(el-> System.out.print(el + "\t")));
    }
}
