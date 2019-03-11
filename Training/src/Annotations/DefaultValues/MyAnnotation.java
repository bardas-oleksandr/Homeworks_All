package Annotations.DefaultValues;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface MyAnnotation {
    double level();
    int value() default 10; //Значение по умолчанию
    String message();
}
