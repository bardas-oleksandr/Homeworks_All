package ua.levelup.aspectj.circus;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class Magician implements MindReader {
    @Pointcut("execution(* ua.levelup.aspectj.circus." +
            "Volunteer.thinkOfSomething(String)) && args(thoughts))")
    public void thinking(String thoughts){} //Параметры метода должны
    //совпадать с параметрами метода thinkOfSomething(String)

    private String thoughts;

    //параметр thoughts из среза точек сопряжения thinking передается
    //методу interceptThoughts(String thoughts)
    @Override
    @Before("thinking(thoughts)")
    public void interceptThoughts(String thoughts) {
        System.out.println("Intercepting volunteer's thoughts");
        this.thoughts = thoughts;
    }

    @Override
    public String getThoughts() {
        return thoughts;
    }
}
