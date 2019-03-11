package Problem_01;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Repeatable(AnnotationContainer.class)
public @interface MyAnnotation {
    String explanation();
}
