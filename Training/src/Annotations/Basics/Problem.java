package Annotations.Basics;

import Interfaces.IProblem;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Problem implements IProblem {
    @Override
    public void solve() {
        Pike pike = new Pike(3);    //Объект, класс которого был аннотирован

        Class<?> clazz = pike.getClass();
        System.out.println(clazz.getName());

        System.out.println("\n=========getAnnotation===============================================");
        SecondAnnotation ann = clazz.getAnnotation(SecondAnnotation.class); //Получаем конкретную аннотацию
        System.out.println(ann);
        System.out.println("level=" + ann.level());  //Мы также можем обращаться к членам аннотации

        try {
            System.out.println("\n=========getConstructor==============================================");
            Constructor fishConstructor = clazz.getConstructor(int.class);
            System.out.println("Constructor: " + fishConstructor);
        }
        catch (NoSuchMethodException e) {
            System.out.println("NoSuchMethodException: " + e.getMessage());
        }

        //ОБОБЩЕНИЕ
        //1. Метод getField() дает доступ к публичным полям из класса и из его суперклассов
        //2. Метод getDeclaredField() дает доступ ко всем собственным (неунаследованным) полям (публичным и непубличным)
        System.out.println("\nСравнение методов getField и getDeclaredField");
        System.out.println("=========getField====================================================");
        try {
            System.out.println("=========public field================================================");
            Field field = clazz.getField("length"); //Поле будет получено так как оно публичное
            System.out.println("field: " + field);
        }
        catch (NoSuchFieldException e) {
            System.out.println("NoSuchFieldException: " + e.getMessage());
        }

        try{
            System.out.println("=========non-public field============================================");
            Field field = clazz.getField("hungry");   //Получим исключение - поле hungry не public
            System.out.println("field: " + field);
        }
        catch (NoSuchFieldException e) {
            System.out.println("NoSuchFieldException: " + e.getMessage());
        }

        try {
            System.out.println("=========public field from superclass================================");
            Field field = clazz.getField("age");  //Поле будет получено так как оно публичное (даже из суперкласса)
            System.out.println("field: " + field);
        }
        catch (NoSuchFieldException e) {
            System.out.println("NoSuchFieldException: " + e.getMessage());
        }

        try{
            System.out.println("=========non-public field from superclass============================");
            Field field = clazz.getField("weight");    //Будет брошено исключение - поле weight не public
            System.out.println("field: " + field);
        }
        catch (NoSuchFieldException e) {
            System.out.println("NoSuchFieldException: " + e.getMessage());
        }

        System.out.println("\n=========getDeclaredField============================================");
        try {
            System.out.println("=========public field================================================");
            Field field = clazz.getDeclaredField("length"); //Поле будет получено так как оно не унаследовано
            System.out.println("field: " + field);
        }
        catch (NoSuchFieldException e) {
            System.out.println("NoSuchFieldException: " + e.getMessage());
        }

        try{
            System.out.println("=========non-public field============================================");
            Field field = clazz.getDeclaredField("hungry"); //Поле будет получено так как оно не унаследовано
            System.out.println("field: " + field);
        }
        catch (NoSuchFieldException e) {
            System.out.println("NoSuchFieldException: " + e.getMessage());
        }

        try {
            System.out.println("=========public field from superclass================================");
            Field field = clazz.getDeclaredField("age");  //Получим исключение - поле было age унаследовано
            System.out.println("field: " + field);
        }
        catch (NoSuchFieldException e) {
            System.out.println("NoSuchFieldException: " + e.getMessage());
        }

        try{
            System.out.println("=========non-public field from superclass============================");
            Field field = clazz.getDeclaredField("weight");    //Получим исключение - поле weight было унаследовано
            System.out.println("field: " + field);
        }
        catch (NoSuchFieldException e) {
            System.out.println("NoSuchFieldException: " + e.getMessage());
        }

        //ОБОБЩЕНИЕ
        //1. Метод getMethod() дает доступ к публичным методам из класса и из его суперклассов
        //2. Метод getDeclaredMethod() дает доступ ко всем собственным (неунаследованным) методам (публичным и непубличным)
        System.out.println("\nСравнение методов getMethod и getDeclaredMethod");
        Method method = null;
        System.out.println("===============GetMethod=============================================");
        try {
            System.out.println("=============public method===========================================");
            method = clazz.getMethod("publicFromHeir"); //Метод будет получен так как он публичный
            System.out.println("Method: " + method);
        }
        catch (NoSuchMethodException e) {
            System.out.println("NoSuchMethodException: " + e.getMessage());
        }

        try {
            System.out.println("=========non-public method===========================================");
            method = clazz.getMethod("privateFromHeir");    //Получим исключение - метод непубличный
            System.out.println("Method: " + method);
        }
        catch (NoSuchMethodException e) {
            System.out.println("NoSuchMethodException: " + e.getMessage());
        }

        try {
            System.out.println("=========public method from superclass===============================");
            method = clazz.getMethod("publicFromParent"); //Метод будет получен так как он публичный (даже из суперкласса)
            System.out.println("Method: " + method);
        }
        catch (NoSuchMethodException e) {
            System.out.println("NoSuchMethodException: " + e.getMessage());
        }

        try {
            System.out.println("=========non-public method from superclass===========================");
            method = clazz.getMethod("privateFromParent");     //Получим исключение - метод непубличный
            System.out.println("Method: " + method);
        }
        catch (NoSuchMethodException e) {
            System.out.println("NoSuchMethodException: " + e.getMessage());
        }

        System.out.println("\n=========getDeclaredMethod===========================================");
        try {
            System.out.println("=============public method===========================================");
            method = clazz.getDeclaredMethod("publicFromHeir"); //Метод будет получен так как он не унаследован
            System.out.println("Method: " + method);
        }
        catch (NoSuchMethodException e) {
            System.out.println("NoSuchMethodException: " + e.getMessage());
        }

        try {
            System.out.println("=========non-public method===========================================");
            method = clazz.getDeclaredMethod("privateFromHeir"); //Метод будет получен так как он не унаследован
            System.out.println("Method: " + method);
        }
        catch (NoSuchMethodException e) {
            System.out.println("NoSuchMethodException: " + e.getMessage());
        }

        try {
            System.out.println("=========public method from superclass===============================");
            method = clazz.getDeclaredMethod("publicFromParent");   //Получим исключение - метод унаследован
            System.out.println("Method: " + method);
        }
        catch (NoSuchMethodException e) {
            System.out.println("NoSuchMethodException: " + e.getMessage());
        }

        try {
            System.out.println("=========non-public method from superclass===========================");
            method = clazz.getDeclaredMethod("privateFromParent");   //Получим исключение - метод унаследован
            System.out.println("Method: " + method);
        }
        catch (NoSuchMethodException e) {
            System.out.println("NoSuchMethodException: " + e.getMessage());
        }

        System.out.println("\nСравнение методов getAnnotations и getDeclaredAnnotations");
        //ОБОБЩЕНИЕ
        //1. Эти два метода отличаются тем, что getDeclaredAnnotations не включает в возвращаемый массив аннотации,
        // которые были унаследованы. Наследоваться аннотации могут только при аннотировании классов (причем наследуются
        // только аннотации, которые сами аннотированы как наследуемые - @Inherited).
        // Поэтому методы getAnnotations и getDeclaredAnnotations отличаются только при использовании по отношению
        // к классам
        System.out.println("=========getAnnotations==============================================");
        Annotation[] list = clazz.getAnnotations();//Тут мы получим все* аннотации из класса Pike
        //ПЛЮС к этому будут добавлены НАСЛЕДУЕМЫЕ (помеченные как @Inherited) аннотации из суперкласса Fish
        //*-все аннотации с политикой удержания RUNTIME
        for (Annotation item : list) {
            System.out.println(item);
        }

        System.out.println("\n=========getDeclaredAnnotations======================================");
        list = clazz.getDeclaredAnnotations();  //Получаем все аннотации непосредственно для класса Pike (без наследуемых)
        for (Annotation item : list) {
            System.out.println(item);
        }
    }
}