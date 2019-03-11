package Classes.AbstractClasses;

public class Heir extends AbstractFather {
    public Heir(int x) {
        super(x);
    }

    @Override
    public void doNothing(){
        System.out.println("Yes, it is");
    }
}
