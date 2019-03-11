package ua.levelup;

import org.springframework.beans.factory.annotation.Autowired;

abstract public class Hero extends LivingBeing {
    private Monster monster;

    public Hero(){
        super();
        this.monster = null;
    }

    public Hero(int power, Monster monster) {
        super(power);
        this.monster = monster;
    }

    abstract void makeTheFight();

    public Monster getMonster() {
        return monster;
    }

    //@Resource(name="monster") выполняет тоже самое что и @Autowired,
    //но дает возможость указывать более точные требования DI
    //@Inject   - тоже самое что и @Autowired
    //Связывание @Autowired выполняется за счет того что аргумент monster совпадает с бином monster
    @Autowired
    public void setMonster(Monster monster) {
        this.monster = monster;
        System.out.println("I am knight. My enemy is ogre\n");
    }
}
