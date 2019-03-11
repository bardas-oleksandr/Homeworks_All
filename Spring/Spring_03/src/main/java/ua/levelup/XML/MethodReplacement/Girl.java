package ua.levelup.XML.MethodReplacement;

public class Girl {
    private String name;

    public Girl(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void speak(String uselessParam){
        System.out.println("I am " + name + ", and you will never see me naked.");
    }
}
