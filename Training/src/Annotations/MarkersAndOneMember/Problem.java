package Annotations.MarkersAndOneMember;

import Interfaces.IProblem;

import java.lang.reflect.Method;

@OneMemberAnnotation(1) //����������� ����� ������ ��� ��������� � ����� ������
public class Problem implements IProblem {

    @AnnotationMarker   //������ ����� ��������� ��� ������ �������������, ���� ��������
    @Override
    public void solve() {
        Class<?> clazz = this.getClass();

        try {
            Method method = clazz.getMethod("solve");
            if (method.isAnnotationPresent(Override.class)) {   //��������� @Override �� �������� �� ������ ��-�� �� RetentionPolicy
                System.out.println("Annotation @Override is present in method solve()");
            }
        } catch (NoSuchMethodException e) {
            System.out.println(e.getMessage());
        }

        try {
            Method method = clazz.getMethod("solve");
            if (method.isAnnotationPresent(AnnotationMarker.class)) {
                System.out.println("Annotation @AnnotationMarker is present in method solve()");
            }
        } catch (NoSuchMethodException e) {
            System.out.println(e.getMessage());
        }
    }
}
