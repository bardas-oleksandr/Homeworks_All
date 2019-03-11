package Annotations.Basics;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface MyAnnotation {    //Все аннотации наследуются от Annotation, поэтому еще одно наследование запрещено
    String message();   //Член интерфейса объявляется как метод без параметров, ведет себя как поле.

}
