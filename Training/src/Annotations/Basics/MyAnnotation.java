package Annotations.Basics;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface MyAnnotation {    //��� ��������� ����������� �� Annotation, ������� ��� ���� ������������ ���������
    String message();   //���� ���������� ����������� ��� ����� ��� ����������, ����� ���� ��� ����.

}
