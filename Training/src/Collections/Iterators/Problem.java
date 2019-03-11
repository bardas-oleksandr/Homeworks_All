package Collections.Iterators;

import Interfaces.IProblem;
import com.sun.jmx.remote.internal.ArrayQueue;

import java.util.*;

public class Problem implements IProblem {
    @Override
    public void solve() {
        Integer[] arr = {1, 2, 3, 4, 5};
        List<Integer> list = new ArrayList<Integer>();
        Collections.addAll(list, arr);

        System.out.println("Step # 1");
        for (Integer integer : list) {
            System.out.println(integer);
        }

        System.out.println("Step # 2");
        Iterator iterator = list.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());    //Возвращает значение и переходит на следующее
        }

        System.out.println("Step # 3");
        iterator = list.iterator();
        iterator.next();    //Перед удалением надо перейти к удаляемому элементу
        iterator.remove();  //Удаляем текущий элемент
        iterator.next();
        iterator.remove();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }

        ListIterator<Integer> lIterator = list.listIterator();
        System.out.println("Forward");
        while(lIterator.hasNext()){
            System.out.println("current: " + lIterator.next() +
                    "\tprevioustIndex: " + lIterator.previousIndex()+
                    "\tnextIndex: " + lIterator.nextIndex());
        }

        System.out.println("Backward");
        while(lIterator.hasPrevious()){
            System.out.println("current: " + lIterator.previous() +
                    "\tprevioustIndex: " + lIterator.previousIndex()+
                    "\tnextIndex: " + lIterator.nextIndex());
        }
    }

}
