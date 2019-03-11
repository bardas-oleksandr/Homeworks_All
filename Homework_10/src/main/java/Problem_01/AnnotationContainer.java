package Problem_01;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface AnnotationContainer {
    MyAnnotation[] value();
}
