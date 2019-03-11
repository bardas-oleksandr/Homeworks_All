package ua.levelup;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class App {
    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("app-context.xml");

        King king = (King) ctx.getBean("king"); //Объект king получен с помощью Dependency Lookup
        //Все внутренние связи объекта king получены с помощью Dependency Injection
        king.speak();
        Minstrel minstrel = (Minstrel) ctx.getBean("minstrel");
        Dragon dragon = (Dragon) ctx.getBean("dragon");
        Knight braveKnight = (Knight) ctx.getBean("braveKnight");
        braveKnight.embarkOnQuest();
        Knight cowardKnight = (Knight) ctx.getBean("cowardKnight");

        Damsel kitty = (Damsel) ctx.getBean("damsel");
        kitty.speak();
        Damsel lilly = (Damsel) ctx.getBean("damsel");
        lilly.speak();

        System.out.println("Coward's damsel list");
        List<Damsel> list = cowardKnight.getDamselList();
        list.forEach((x)-> System.out.println(x));

        System.out.println("\nHandsome guy's damsel list");
        Guy handsomeGuy = (Guy)ctx.getBean("handsomeGuy");
        Map<String,Damsel> map = handsomeGuy.getDamselMap();
        Set<String> keys = map.keySet();
        for (String key:keys) {
            System.out.println(map.get(key) + "\tNotice that:" + key);
        }

        System.out.println("\nReach guy's damsel list");
        Guy reachGuy = (Guy)ctx.getBean("reachGuy");
        Map<String,Damsel> map1 = reachGuy.getDamselMap();
        Set<String> keys1 = map1.keySet();
        for (String key:keys1) {
            System.out.println(map1.get(key) + "\tNotice that:" + key);
        }

        Guy uglyGuy = (Guy)ctx.getBean("uglyGuy");
        if(uglyGuy.getDamselMap()==null){
            System.out.println("\nUgly guy is still a virgin\n");
        }

        System.out.println("First princess");
        Princess princess = (Princess)ctx.getBean("princess");
        System.out.println(princess);

        System.out.println("\nSecond princess");
        Princess prettyPrincess = (Princess)ctx.getBean("prettyPrincess");
        System.out.println(prettyPrincess);

        Damsel oldDamsel = (Damsel)ctx.getBean("oldDamsel");
        System.out.println("\nOld damsel says:");
        oldDamsel.speak();

        System.out.println("Cities");

    }
}