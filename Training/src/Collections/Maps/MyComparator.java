package Collections.Maps;

import java.util.Comparator;

public class MyComparator<T extends String> implements Comparator<T> {
    @Override
    public int compare(T o1, T o2) {
        return o2.length() - o1.length();
    }
}
