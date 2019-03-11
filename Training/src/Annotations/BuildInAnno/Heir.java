package Annotations.BuildInAnno;

@MyRepeatableAnnotation(3)
@MyRepeatableAnnotation(4)
@MyRepeatableAnnotation(5)
public class Heir extends Parent {
    @Override
    @MyRepeatableAnnotation(6)
    @MyRepeatableAnnotation(7)
    @MyRepeatableAnnotation(8)
    public void doNothing(){

    }
}
