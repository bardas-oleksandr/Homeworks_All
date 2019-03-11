package ua.levelup;

public class King {
    private King(){ }

    private static class KingHolder{
        static King instance = new King();
    }

    public static King getInstance(){
        return KingHolder.instance;
    }

    public void speak(){
        System.out.println("I will give a chest of gold, whoever will kill the dragon\n");
    }
}
