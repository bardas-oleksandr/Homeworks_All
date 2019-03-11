package Lambdas.Basics;

public class SuperCaller extends Parent {

    public int show1(){
        return this.callMethodFromSuper(this::getValue);
    }

    public int show2(){
        return this.callMethodFromSuper(super::getValue);
    }

    public int callMethodFromSuper(FISimple obj){
        return obj.getValue();
    }
}
