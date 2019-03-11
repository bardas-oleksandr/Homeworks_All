package Annotations;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Repeatable(AnnotationContainer.class)
@Target(ElementType.FIELD)
public @interface DefFlower {
    Class flowerType();
    String color();
    double price();
}
