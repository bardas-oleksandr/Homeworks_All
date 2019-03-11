package Shop;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;

public class MyObjectInputStream extends ObjectInputStream {
    public MyObjectInputStream(InputStream in) throws IOException {
        super(in);
    }

    @Override
    public String readLine() throws IOException{
        StringBuilder builder = new StringBuilder();
        char ch = 0;
        while((ch = this.readChar()) != '\n'){
            builder.append(ch);
        }
        return new String(builder);
    }
}
