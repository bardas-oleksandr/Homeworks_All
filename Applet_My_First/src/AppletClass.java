/*
<applet code="SimpleApplet" width=300 height=12O> </applet>
*/

import java.applet.Applet;
import java.awt.*;

public class AppletClass extends Applet {
    @Override
    public void paint(Graphics g){
        g.drawString("Hello", 20, 20);
    }

}
