package Classes.AnonymousClasses;

import Interfaces.IProblem;

public class Problem implements IProblem {
    @Override
    public void solve() {
        int x=1;
        int y=1;
        y++;    //not effectively final

        A a = new A(5){
            //ѕереопределенный метод родительского класса ј
            @Override
            public int getX(){
                System.out.println(x);
                //System.out.println(y); ¬ анонимном классе можно обращатьс€ только к final
                //или effectively final переменным
                this.newMethod();
                return super.getX()*2;
            }

            //Ќовый метод, определенный в анонимном классе. ќн доступен только
            //внутри анонимного класса
            public void newMethod(){
                System.out.println("Yes, it is.");
            }
        };

        System.out.println(a.getX());

        //System.out.println(a.newMethod());    //ћы не можем получить доступ к методу анонимного класса
        //через ссылку типа родительского классе
    }
}
