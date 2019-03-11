package ua.levelup;

public class SimpleMessageRenderer implements MessageRenderer {
    private MessageProvider messageProvider;

    public SimpleMessageRenderer(){
        this.messageProvider = null;
    }

    public SimpleMessageRenderer(MessageProvider messageProvider){
        this.messageProvider = messageProvider;
    }

    @Override
    public void render() {
        System.out.println(this.messageProvider.getMessage().toUpperCase());
    }

    @Override
    public MessageProvider getMessageProvider() {
        return this.messageProvider;
    }

    @Override
    public void setMessageProvider(MessageProvider messageProvider) {
        this.messageProvider = messageProvider;
    }
}
