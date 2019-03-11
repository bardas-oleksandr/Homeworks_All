package Annotations.BuildInAnno;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME) //@Retention - ���������� ��������� ��� ����������� �������� ��������� ���������
@Inherited //���������-������ ��� ���������� ����������� ���������
@Documented //�������� ��� ��������� ������ ���� ���������������. ��� ��� ��������?
@Target(ElementType.TYPE)   //���������� ��� ����� ������������ ���������� (� ������ ������� - ������)
public @interface MyAnnotation {
    int value() default 10;
}
