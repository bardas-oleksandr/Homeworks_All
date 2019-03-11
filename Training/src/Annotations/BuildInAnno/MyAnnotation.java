package Annotations.BuildInAnno;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME) //@Retention - встроенная аннотация для определения политики удержания аннотации
@Inherited //Аннотация-маркер для маркировки наследуемой аннотации
@Documented //Означает что аннотация должна быть документирована. КАК ЭТО РАБОТАЕТ?
@Target(ElementType.TYPE)   //Определяет что можно аннотировать аннотацией (а данном примере - классы)
public @interface MyAnnotation {
    int value() default 10;
}
