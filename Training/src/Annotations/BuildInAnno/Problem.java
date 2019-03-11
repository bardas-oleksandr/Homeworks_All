package Annotations.BuildInAnno;

import Interfaces.IProblem;

import java.lang.annotation.Annotation;
import java.lang.annotation.Native;
import java.lang.annotation.Repeatable;
import java.lang.reflect.Method;

@MyAnnotation
public class Problem implements IProblem {
    @Override   //���������� ��������� �� ������ java.lang
    //@MyAnnotation //��������� �� ����� ���� ��������� � ������ ��� ��� ��� ���� �������� @Target(ElementType.TYPE)
    public void solve() {
        Heir heir = new Heir();
        Class<?> clazz = heir.getClass();

        Annotation anno = clazz.getAnnotation(AnnotationContainer.class);   //��� �������� ������ ���� ��������� ������������� �����������
        System.out.println(anno);

        Annotation[] annos = clazz.getAnnotationsByType(MyRepeatableAnnotation.class);  //��� �������� ������
        for(Annotation item: annos){
            System.out.println(item);
        }

        try{
            Method method = clazz.getMethod("doNothing");
            anno = method.getAnnotation(AnnotationContainer.class);   //��� �������� ������ ���� ��������� ������������� �����������
            System.out.println(anno);

            annos = method.getAnnotationsByType(MyRepeatableAnnotation.class);  //��� �������� ������
            for(Annotation item: annos){
                System.out.println(item);
            }
        }catch(NoSuchMethodException e){
            System.out.println(e.getMessage());
        }


        //@Native - ��� ������������ �����, ��������� �� ������������-���������������� ����
        //@Deprecated - ��� ���������� ��������������� ������� (�������� ����� finalize())
        //@SafeVarargs    //��������� ��� ���������� �������������� ���������� ������� � ����������� ���������� �����
    }

    @SuppressWarnings("unused") //��������� �������������� ����������� (��� ���������).
                                //� ������ ������ ����������� �������������� � ��� ��� ����� ������� �� ������������
    public void veryImportantMethod(){

    }
}