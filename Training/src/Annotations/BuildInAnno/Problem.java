package Annotations.BuildInAnno;

import Interfaces.IProblem;

import java.lang.annotation.Annotation;
import java.lang.annotation.Native;
import java.lang.annotation.Repeatable;
import java.lang.reflect.Method;

@MyAnnotation
public class Problem implements IProblem {
    @Override   //Встроенная аннотация из пакета java.lang
    //@MyAnnotation //Аннотация не может быть применена к методу так как она была помечена @Target(ElementType.TYPE)
    public void solve() {
        Heir heir = new Heir();
        Class<?> clazz = heir.getClass();

        Annotation anno = clazz.getAnnotation(AnnotationContainer.class);   //Это работает только если аннотация действительно повторялась
        System.out.println(anno);

        Annotation[] annos = clazz.getAnnotationsByType(MyRepeatableAnnotation.class);  //Это работает всегда
        for(Annotation item: annos){
            System.out.println(item);
        }

        try{
            Method method = clazz.getMethod("doNothing");
            anno = method.getAnnotation(AnnotationContainer.class);   //Это работает только если аннотация действительно повторялась
            System.out.println(anno);

            annos = method.getAnnotationsByType(MyRepeatableAnnotation.class);  //Это работает всегда
            for(Annotation item: annos){
                System.out.println(item);
            }
        }catch(NoSuchMethodException e){
            System.out.println(e.getMessage());
        }


        //@Native - для анноирования полей, досутпных из платформенно-ориентированного кода
        //@Deprecated - для маркировки нерекомендуемых средств (например метод finalize())
        //@SafeVarargs    //Аннотация для подавления предупреждений касательно методов с аргументами переменной длины
    }

    @SuppressWarnings("unused") //Подавляет предупреждение компилятора (или несколько).
                                //В данном случае подавляется предупреждение о том что метод никогда не используется
    public void veryImportantMethod(){

    }
}