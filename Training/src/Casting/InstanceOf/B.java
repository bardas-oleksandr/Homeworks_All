package Casting.InstanceOf;

import com.sun.org.glassfish.external.probe.provider.annotations.Probe;

public class B extends A {
    private int y;

    public B(){
        super();
        this.y = 6;
    }

    @Override
    public String toString(){
        return new String("x=" + super.x() + "\ty=" + this.y);
    }
}
