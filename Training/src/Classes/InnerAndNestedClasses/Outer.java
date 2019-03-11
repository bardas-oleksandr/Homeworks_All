package Classes.InnerAndNestedClasses;

public class Outer {
    private int outerValue;

    //1 вид вложенных классов
    //Static nested class or Member of outer class
    static class StaticInner{
        public void show(){
            System.out.println("Static inner class");
            //В статическом вложенном классе нельзя обращаться к полям внешнего класса
            //Потому что объект статического внутреннего класса можно создать отдельно от
            //внешеного, соответственно этих полей может просто не быть
            //System.out.println("I can see my outer's fields: " + Outer.this.outerValue);
            pike.speak();
            //perch.speak();    //В статическом классе нельзя обращаться к нестатическому
        }
    }

    //2 вид вложенных классов
    //Nested inner class
    public class Inner{
        public void show(){
            System.out.println("Inner class");
            System.out.println("I can see my outer's fields: " + Outer.this.outerValue);
            pike.speak();
            perch.speak();
        }

        //Нестатический внутренний класс не может содержать статических методов
//        public static void doNothing(){
//            System.out.println("Hello");
//        }
    }

    //3 вид вложенных классов - анонимные
    //3.1 Static anonymous nested class
    static Fish pike = new Fish(){
        @Override
        public void speak(){
            System.out.println("I am pike");
        }
    };

    //3.2 Anonymous inner class
    Fish perch = new Fish(){
        @Override
        public void speak(){
            System.out.println("I am perch");
        }
    };

    public void doNothing(){
        int x = 1;
        final int finalX = 1;

        //4 вид вложенных классов
        //Method local inner
        class Animal{
            public void speak(){
                System.out.println("I am just an animal");
                System.out.println("I can see final local variables: " + finalX);
                System.out.println("I can see non-final local variables: " + x);
            }
        }

        Animal animal = new Animal();
        animal.speak();
    }
}
