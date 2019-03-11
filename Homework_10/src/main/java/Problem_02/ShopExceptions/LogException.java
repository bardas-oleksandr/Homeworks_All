package Problem_02.ShopExceptions;

public class LogException extends Exception {
    public LogException(Throwable e){
        super(e);
    }

    public LogException(String str){
        super(str);
    }

    public LogException(){
        super();
    }
}
