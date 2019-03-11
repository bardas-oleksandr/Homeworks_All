package Annotations.BuildInAnno;

@MyRepeatableAnnotation(1)  //��� ��������� � ������-���������� ������ ����� �������� ��� ������������� (@Repeatable)
// ���� �������� �� �� ��� ��� �������� ��� @Inherited
public class Parent {
    @MyRepeatableAnnotation(2)
    public void doNothing(){

    }
}