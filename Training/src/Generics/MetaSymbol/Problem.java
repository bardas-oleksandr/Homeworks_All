package Generics.MetaSymbol;

import Interfaces.IProblem;

public class Problem implements IProblem {
    @Override
    public void solve() {
        Coord<Point2D> c1 = new Coord<Point2D>(new Point2D());
        showXY(c1);
        showXY_Meta(c1);
        //showXYZ(c1);  //Это не компилируется благодаря метасимвольному ограничению

        Coord<Point3D> c2 = new Coord<Point3D>(new Point3D());
        showXY(c2);
        showXY_Meta(c2);
        showXYZ(c2);

        Coord<?> one = c1.getMe();
    }

    //Написать вместо знака ? знак Т мы не можем. Букву Т можно писать в объявлении типа дженерика, а тут у нас уже использование типа
    public static void showXY_Meta(Coord<?> coord){
        System.out.println("X=" + coord.point.x);
        System.out.println("Y=" + coord.point.y);
    }

    //без метасимвола “?” мы получим так называемый базовый тип – по сути непараметризированный дженерик.
    //Возможность пользоваться базовыми типами дана для облегчения работы со старым кодом, в котором еще не было
    //дженериков. Лучше этим не пользоваться
    public static void showXY(Coord coord){
        System.out.println("X=" + coord.point.x);
        System.out.println("Y=" + coord.point.y);
    }

    public static void showXYZ(Coord<? extends Point3D> coord){
        System.out.println("X=" + coord.point.x);
        System.out.println("Y=" + coord.point.y);
        System.out.println("Z=" + coord.point.z);
    }

    //Этому методу можно передавать только объекты суперклассов класса Point3D
    public static void showXY_Meta_LowerBound(Coord<? super Point3D> coord){
        System.out.println("X=" + coord.point.x);
        System.out.println("Y=" + coord.point.y);
    }
}
