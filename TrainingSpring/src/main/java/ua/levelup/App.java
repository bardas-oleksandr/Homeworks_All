package ua.levelup;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App
{
    public static void main( String[] args ) {
        //Без спринга
        MessageSupportFactory factory = MessageSupportFactory.INSTANCE;
        MessageRenderer renderer = factory.getMessageRenderer();
        renderer.setMessageProvider(factory.getMessageProvider());
        renderer.render();

        //Со спрингом
        ApplicationContext ctx = new ClassPathXmlApplicationContext
                ("app-context.xml");
        MessageRenderer springRenderer = (MessageRenderer) ctx.getBean("renderer");
        springRenderer.render();
    }
}
