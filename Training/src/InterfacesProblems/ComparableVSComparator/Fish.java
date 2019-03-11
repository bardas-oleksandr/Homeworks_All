package InterfacesProblems.ComparableVSComparator;

import java.util.Comparator;

public class Fish implements Comparable<Fish> {
    private String name;
    private int weight;

    Fish(String name, int weight){
        this.name = name;
        this.weight = weight;
    }

    @Override
    public int compareTo(Fish o) {
        return this.weight - o.weight;
    }

    int useComparator(Comparator<Fish> comparator, Fish other){
        return comparator.compare(this, other);
    }

    @Override
    public String toString(){
        return new String(name + " (weight=" + weight + ")");
    }

    String name(){
        return this.name;
    }

    int weight(){
        return this.weight;
    }
}
