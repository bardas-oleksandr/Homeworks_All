package ua.levelup;

import org.springframework.beans.factory.BeanCreationException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App
{
    public static void main( String[] args ) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("app-context.xml");
        Hero hero = (Hero) ctx.getBean("knight");
        hero.makeTheFight();


        Story story = null;
        try{
            story = (Story)ctx.getBean("story");
        }catch (BeanCreationException e){
            System.out.println("Bean was not ctreated");
        }
    }
}
