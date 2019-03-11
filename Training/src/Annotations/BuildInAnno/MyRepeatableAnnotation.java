package Annotations.BuildInAnno;

import java.lang.annotation.Inherited;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(AnnotationContainer.class) //- ��� ��������� ������������� ���������
public @interface MyRepeatableAnnotation {
    int value();
}
