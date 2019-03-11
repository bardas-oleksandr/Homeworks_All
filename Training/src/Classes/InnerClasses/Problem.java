package Classes.InnerClasses;

import Interfaces.IProblem;

public class Problem implements IProblem {
    public void solve(){
        Outer outer = new Outer(2,3);
        System.out.println("outer.getxOuter(): " + outer.getxOuter());
        System.out.println("outer.getInner(): " + outer.getInner());
        System.out.println("outer.getInner().getxInner(): " + outer.getInner().getxInner());
        System.out.println("outer.getStaticInner(): " + outer.getStaticInner());
        System.out.println("outer.getStaticInner().getxStaticInner(): " + outer.getStaticInner().getxStaticInner());

        Outer.Inner inner = new Outer(2,3).new Inner(4);    //������ ����������� ������ ����� ���� ������ ������ ����� �������� �������� ������
        System.out.println("inner.getxInner(): " + inner.getxInner());
        System.out.println("inner.getOuter().getxOuter(): " + inner.getOuter().getxOuter());

        //������� �����. ���� � ��� ��� � ��� ���� ������ Inner, ������� ������ � ��������� ������������� ������� Outer
        //������ �������� ���� ��� ���� ������ Inner
        System.out.println("inner.getOuter().getInner().getxInner(): " + inner.getOuter().getInner().getxInner());

        Outer.StaticInner staticInner = new Outer.StaticInner();   //������ ������������ ������ ��������� ��� ������� ����������� ������
        System.out.println("staticInner.getxStaticInner(): " + staticInner.getxStaticInner());
    }
}
