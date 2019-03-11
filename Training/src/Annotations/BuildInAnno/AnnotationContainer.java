package Annotations.BuildInAnno;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Inherited
@Retention(RetentionPolicy.RUNTIME)
public @interface AnnotationContainer {
    MyRepeatableAnnotation[] value();   //Массив повторяющихся аннотаций типа MyRepeatableAnnotation
}
