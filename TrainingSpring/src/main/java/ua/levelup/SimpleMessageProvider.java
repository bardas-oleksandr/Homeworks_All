package ua.levelup;

public class SimpleMessageProvider implements MessageProvider {
    private String message;

    SimpleMessageProvider(){
        this.message = "Yes, it is";
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
