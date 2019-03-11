package ua.levelup;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public enum MessageSupportFactory {
    INSTANCE;

    private Properties properties;
    private MessageProvider messageProvider;
    private MessageRenderer messageRenderer;

    MessageSupportFactory() {
        this.properties = new Properties();
        try {
            properties.loadFromXML(new FileInputStream("src/main/resources/prop.txt"));
            this.messageProvider = (MessageProvider) Class.forName(this.properties
                    .getProperty("provider")).newInstance();
            this.messageRenderer = (MessageRenderer) Class.forName(this.properties
                    .getProperty("renderer")).newInstance();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public MessageProvider getMessageProvider() {
        return this.messageProvider;
    }

    public MessageRenderer getMessageRenderer() {
        return this.messageRenderer;
    }
}
