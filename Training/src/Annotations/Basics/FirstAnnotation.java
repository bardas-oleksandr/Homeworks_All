package Annotations.Basics;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@Inherited  //��� ��������� ����� �������������
public @interface FirstAnnotation {
    int value();
}
