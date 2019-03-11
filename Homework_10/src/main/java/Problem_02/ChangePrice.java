package Problem_02;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Inherited
public @interface ChangePrice {
    boolean change();
    double maxValue();
}
