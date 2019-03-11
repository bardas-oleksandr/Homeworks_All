package Problem_03;

import Interfaces.IProblem;
import Interfaces.IRandomizer;

import java.util.*;

public class Problem implements IProblem {
    @Override
    public void solve() {
        final int SIZE = 20;
        Integer[] arr = new Integer[SIZE];

        IRandomizer<Integer> rnd = (maximum) -> {
            return new Random().nextInt(maximum);
        };

        for (int i = 0; i < SIZE; i++) {
            arr[i] = rnd.nextValue(100);
        }

        List<Integer> list = new ArrayList<>();
        list.addAll(Arrays.asList(arr));
        System.out.println(list);

        List<Integer> summList = summator(list);
        System.out.println(summList);
    }

    public List<Integer> summator(List<Integer> c) {
        if (c.size() == 1) {
            return c;
        } else {
            List<Integer> nextList = new LinkedList<>();
            Iterator<Integer> iterator = c.iterator();
            while (iterator.hasNext()) {
                Integer val = new Integer(iterator.next());
                if (iterator.hasNext()) {
                    val += iterator.next();
                }
                nextList.add(val);
            }
            return summator(nextList);
        }
    }
}
