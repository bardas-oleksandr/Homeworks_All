//ВАЖНОЕ ПРИМЕЧАНИЕ
//Путь к файлу *.class относительно папки WEB-INF/classes должен повторять путь к текущему
//(этому) файлу относительно папки src

package servlets;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class AddCookieServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        try (PrintWriter pw = response.getWriter()) {
            String name = request.getParameter("name");
            String password = request.getParameter("password");
            String alienOrigin = request.getParameter("alien_origin");

            StringBuilder builder = new StringBuilder();
            builder.append("Name:");
            builder.append(name);
            builder.append("Alien:");
            builder.append(alienOrigin);
            builder.append("Password:");
            builder.append(password);
            String message = new String(builder);
            Cookie cookie = new Cookie("MyCookie", message);
            try{
                response.addCookie(cookie);
            }catch (Exception e){
                pw.println(e.getMessage());
                pw.println(e.getCause());
            }
            pw.println("My cookie has been sent to " + message);

            pw.println("<br><br><p>Name: " + name + "</p>");
            pw.println("<p>Alien: " + alienOrigin + "</p>");
            pw.println("<p>Password: " + password + "</p>");

            String addres = request.getRemoteAddr();
            String host = request.getRemoteHost();
            String scheme = request.getScheme();
            String serverName = request.getServerName();
            int port = request.getServerPort();
            pw.println("<p>Addres: " + addres + "</p>");
            pw.println("<p>Host: " + host + "</p>");
            pw.println("<p>Scheme: " + scheme + "</p>");
            pw.println("<p>Port: " + port + "</p>");

        }
    }
}
