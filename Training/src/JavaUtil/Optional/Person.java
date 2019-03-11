package JavaUtil.Optional;

import java.util.Optional;

public class Person {
    private String name;
    private Adress adress;

    public Person(String name){
        this.name = name;
        this.adress = null;
    }

    public Person(String name, Adress adress){
        this.name = name;
        this.adress = adress;
    }

    static public Optional<Adress> getOptionalAdress(Person person){
        return person.adress == null? Optional.empty(): Optional.of(person.adress);
    }

    static public Adress getStaticAdress(Person person){
        return person.adress;
    }

    public Adress getAdress(){
        return this.adress;
    }

    @Override
    public String toString(){
        return "name: " + name + "\t\tadress: " + adress;
    }
}
