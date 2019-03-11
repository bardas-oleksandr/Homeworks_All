package Casting.InstanceOf;

public class C extends A {
    private int z;

    public C(){
        super();
        this.z = 7;
    }

    @Override
    public String toString(){
        return new String("x=" + super.x() + "\tz=" + this.z);
    }
}
