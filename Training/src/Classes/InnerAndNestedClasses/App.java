package Classes.InnerAndNestedClasses;

public class App
{
    void outerMethod() {
        int x = 98;
        class Inner {
            public void innerMethod() {
                System.out.println("x = " + x);
            }
        }
        Inner inner = new Inner();
        inner.innerMethod();
    }

    public static void staticMethod(){
        App app = new App();
        app.outerMethod();
    }
}
