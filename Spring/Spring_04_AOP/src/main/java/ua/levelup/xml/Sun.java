package ua.levelup.xml;

import org.aspectj.lang.ProceedingJoinPoint;

public class Sun {
    public void shine(ProceedingJoinPoint joinpoint) {
        try {
            System.out.println("The sun is rising");
            joinpoint.proceed();
            System.out.println("The sun is getting real low");
        } catch (DeadKnightException e){
            System.out.println("Around method: The knight is dead");
            throw new DeadKnightException();    //Пробрасываем исключение дальше.
            //Это приведет к активации метода afterThrowingMethod
            //а потом надо будет также его руками ловить в main.
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    public void afterThrowingMethod(){
        System.out.println("After-throwing method is done: " +
                "Everybody in the kingdom knows that the knight is dead");
    }
}
