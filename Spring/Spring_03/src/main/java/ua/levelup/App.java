package ua.levelup;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import ua.levelup.XML.CreateAbstract.Warrior;
import ua.levelup.XML.LookupMethodInjection.Guy;
import ua.levelup.XML.MethodReplacement.Girl;
import ua.levelup.XML.Publish.OldStoryteller;
import ua.levelup.XML.Publish.Storyteller;

public class App {
    public static void main( String[] args ) {

        ClassPathXmlApplicationContext parent = new ClassPathXmlApplicationContext("parent.xml");
        parent.getBean("braveKnight");
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext(
                new String[]{"app-context.xml"}, parent);

        Storyteller storyteller = (Storyteller)ctx.getBean("storyteller");
        storyteller.tellTheStory();

        System.out.println("============Old storyteller knows much more stories=============\n");
        OldStoryteller oldStoryteller = (OldStoryteller)ctx.getBean("oldStoryteller");
        oldStoryteller.tellAllTheStories();

        System.out.println("============Annotated Old storyteller knows much more stories===\n");
        ua.levelup.Anno.Publish.OldStoryteller oldMan = (ua.levelup.Anno.Publish.OldStoryteller)
                ctx.getBean("oldMan");
        oldMan.tellAllTheStories();

        System.out.println("============Testing Casanova====================================\n");
        Guy casanova = (Guy)ctx.getBean("casanova");
        casanova.findDamsel();
        casanova.findDamsel();
        casanova.findDamsel();
        casanova.findDamsel();

        Guy tribbiani = (Guy)ctx.getBean("abstractCasanova");
        tribbiani.findDamsel();
        tribbiani.findDamsel();
        tribbiani.findDamsel();
        tribbiani.findDamsel();

        System.out.println("============Creating object of abstract class===================");
        Warrior archer = (Warrior)ctx.getBean("archer");
        archer.attack();

        System.out.println("\n============Testing girl========================================");
        System.out.println("Original behavior:");
        Girl girl = (Girl)ctx.getBean("girl");
        girl.speak("Hello, slutty");

        System.out.println("Replaced behavior:");
        Girl slut = (Girl)ctx.getBean("slut");
        slut.speak("Hello, slutty");

    }
}
