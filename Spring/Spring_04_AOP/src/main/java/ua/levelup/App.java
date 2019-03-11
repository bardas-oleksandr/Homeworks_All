package ua.levelup;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import ua.levelup.xml.circus.Magician;
import ua.levelup.xml.circus.MindReader;
import ua.levelup.xml.circus.Thinker;
import ua.levelup.xml.DeadKnightException;
import ua.levelup.xml.Dragon;
import ua.levelup.xml.Knight;
import ua.levelup.xml.Singer;

public class App {
    public static void main( String[] args ) {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("app-context.xml");
        Dragon dragon = (Dragon)ctx.getBean("dragon");
        Knight knight = (Knight)ctx.getBean("knight");

        dragon.steal();

        try{
            knight.embark();
        }catch (DeadKnightException e){
            System.out.println("Exception was finally caught. Everybody knows that the knight is dead");
        }


        Thinker volunteer = (Thinker)ctx.getBean("volunteer");
        MindReader magician = (Magician)ctx.getBean("magician");
        volunteer.thinkOfSomething("Yes, it is");
        System.out.println("Magician says, that volunteer's thought is:\t" + magician.getThoughts());
        System.out.println("Volunteer's real thought is:\t" + volunteer.getThoughts());


        //Дракон поет как певец. Хотя интерфейс Singer не имплементирован
        ((Singer)dragon).sing();

        System.out.println("\n========================================================================\n");
        ua.levelup.aspectj.Dragon bigDragon = (ua.levelup.aspectj.Dragon)ctx.getBean("bigDragon");
        bigDragon.steal();

        ua.levelup.aspectj.circus.Thinker thinker = (ua.levelup.aspectj.circus.Thinker)ctx
                .getBean("annotatedVolunteer");
        ua.levelup.aspectj.circus.MindReader mindReader = (ua.levelup.aspectj.circus.Magician)ctx
                .getBean("annotatedMagician");
        thinker.thinkOfSomething("Yes, it is");
        System.out.println("Magician says, that volunteer's thought is:\t" + mindReader.getThoughts());
        System.out.println("Volunteer's real thought is:\t" + thinker.getThoughts());

        ((ua.levelup.aspectj.Singer)bigDragon).sing();
    }
}
