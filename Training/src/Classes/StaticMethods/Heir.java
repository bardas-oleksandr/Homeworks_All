package Classes.StaticMethods;

public class Heir extends Parent {
    private int x;

    //@Override //����� ��������� �������� � ������ �� ����� ����������. ������� - ���������� ����� ������� �������������� ��� �� ������
                //���� ����������� ������ �� ����� ������������� � ���� ������������
    public static String speak(){
        return "I am your son";
    }

    public void staticCaller(){
        this.speak();   //����������� ����� ����� ������� ����� this
    }
}
