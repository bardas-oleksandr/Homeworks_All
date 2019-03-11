package Annotations.DefaultValues;

import Interfaces.IProblem;

@MyAnnotation(level=1, message = "Hello")   //���� value �� �� ��������� - ����� ������� �������� �� ���������
public class Problem implements IProblem {
    @Override
    public void solve() {
        Class<?> clazz = this.getClass();

        MyAnnotation annotation = clazz.getAnnotation(MyAnnotation.class);

        System.out.println(annotation);
    }
}
