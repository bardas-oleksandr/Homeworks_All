package Classes.AnonymousClasses;

import Interfaces.IProblem;

public class Problem implements IProblem {
    @Override
    public void solve() {
        int x=1;
        int y=1;
        y++;    //not effectively final

        A a = new A(5){
            //���������������� ����� ������������� ������ �
            @Override
            public int getX(){
                System.out.println(x);
                //System.out.println(y); � ��������� ������ ����� ���������� ������ � final
                //��� effectively final ����������
                this.newMethod();
                return super.getX()*2;
            }

            //����� �����, ������������ � ��������� ������. �� �������� ������
            //������ ���������� ������
            public void newMethod(){
                System.out.println("Yes, it is.");
            }
        };

        System.out.println(a.getX());

        //System.out.println(a.newMethod());    //�� �� ����� �������� ������ � ������ ���������� ������
        //����� ������ ���� ������������� ������
    }
}
