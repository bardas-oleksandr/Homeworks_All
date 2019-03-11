package ua.levelup;

public class Dragon {
    int power;
    int age;

    public Dragon(int power, int age){
        this.power = power;
        this.age = age;
    }

    public int getPower(){
        return this.power;
    }

    public void wakingUp(){
        System.out.println("The dragon is waking up\n");
    }

    public void fire(){
        System.out.println("Dragon is exhaling fire\n");
    }
}
