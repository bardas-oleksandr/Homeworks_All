package Stream_API.Basics;

public class Fish implements Comparable<Fish> {
    private String name;
    private int weight;
    private int age;

    Fish(String name, int weight, int age){
        this.name = name;
        this.weight=weight;
        this.age=age;
    }

    public String name(){
        return this.name;
    }

    public int weight(){
        return this.weight;
    }

    @Override
    public String toString(){
        return name + "\tweight: " + weight + "\tage: " + age;
    }

    @Override
    public int compareTo(Fish o) {
        return this.weight - o.weight;
    }
}
