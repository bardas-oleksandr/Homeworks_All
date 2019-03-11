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

        Outer.Inner inner = new Outer(2,3).new Inner(4);    //Объект внутреннего класса может быть создан только через создание внешнего класса
        System.out.println("inner.getxInner(): " + inner.getxInner());
        System.out.println("inner.getOuter().getxOuter(): " + inner.getOuter().getxOuter());

        //Сложная схема. Суть в том что у нас есть объект Inner, который создан в контексте существования объекта Outer
        //внутри которого есть еще один объект Inner
        System.out.println("inner.getOuter().getInner().getxInner(): " + inner.getOuter().getInner().getxInner());

        Outer.StaticInner staticInner = new Outer.StaticInner();   //Объект статического класса коздается без объекта объемлющего класса
        System.out.println("staticInner.getxStaticInner(): " + staticInner.getxStaticInner());
    }
}
