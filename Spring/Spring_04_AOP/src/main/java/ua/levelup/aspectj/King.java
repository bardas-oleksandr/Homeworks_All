package ua.levelup.aspectj;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

@Aspect
public class King {
    @Pointcut("execution(* ua.levelup.aspectj.Dragon.steal(..))")   //* - любой тип возвращаемого значения
    //(..) - любой список аргументов
    public void kidnapping(){}  //Этот метод приходится добавить в класс. Имя метода определяет имя среза
    //точек сопряжения. Тело метода по сути должно быть пустым, хотя не обязательно.

    @Before("kidnapping()")
    public void speak() {
        System.out.println("Before method");
    }

    @After("kidnapping()")
    public void askAboutHelp() {
        System.out.println("After method");
    }

    @Around("kidnapping()")
    public void aroundMethod(ProceedingJoinPoint joinPoint){
        System.out.println("Doing something before");
        try {
            joinPoint.proceed();    //Вызов метода, обернутого в метод @Around
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        System.out.println("Doing something after");
    }
}
