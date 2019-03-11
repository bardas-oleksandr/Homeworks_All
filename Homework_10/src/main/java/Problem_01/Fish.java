package Problem_01;

@MyAnnotation(explanation = "В учебных целях класс содержит поля и методы со всеми возможными модификаторами доступа")
abstract public class Fish {
    public int weight;
    private int length;
    protected int health;
    int age;

    public Fish(){
        this.weight = 0;
        this.length = 0;
        this.health = 0;
        this.age = 0;
    }

    private Fish(int weight){
        this.weight = weight;
        this.length = 0;
        this.health = 0;
        this.age = 0;
    }

    public Fish(int weight, int length, int health, int age){
        this.weight = weight;
        this.length = length;
        this.health = health;
        this.age = age;
    }

    public int getWeight(){
        return this.weight;
    }

    private int getHealth(){
        return this.health;
    }

    protected void swim(){
        System.out.println("I can swim");
    }

    void eat(int food){
        this.health += food;
        this.weight += food/2 + 1;
        this.length += food/4 + 1;
    }
}
