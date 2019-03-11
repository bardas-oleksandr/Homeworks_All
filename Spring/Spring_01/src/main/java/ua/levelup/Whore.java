package ua.levelup;

public class Whore extends Damsel {
    private String name;

    public Whore(String name){
        super();
        this.name = name;
    }

    @Override
    public String toString(){
        return "Hi sweety, I am " + this.name;
    }
}
