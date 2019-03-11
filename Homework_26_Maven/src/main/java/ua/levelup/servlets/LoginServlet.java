package ua.levelup.servlets;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

public class LoginServlet extends HttpServlet {
    private final String USER_NAME = "user_name";
    private final String PASSWORD = "password";

    @Override
    public void service (ServletRequest request, ServletResponse response)
            throws ServletException, IOException{
        response.setContentType("text/html");
        try(PrintWriter pw = response.getWriter()){
            try {
                final String JDBC_DRIVER="org.postgresql.Driver";
                Class.forName(JDBC_DRIVER);
                String user="postgres";
                String password="He453EC8";
                String url="jdbc:postgresql://localhost:1901/WildLifeTest";
                try(Connection connection = DriverManager.getConnection(url,user,password)){
                    String userName = request.getParameter(USER_NAME);
                    String userPassword = request.getParameter(PASSWORD);

                    String sql = "SELECT password FROM users WHERE login = ?";
                    PreparedStatement statement = connection.prepareStatement(sql);
                    statement.setString(1,userName);
                    ResultSet resultSet = statement.executeQuery();
                    String realPassword = null;
                    int count = 0;
                    while(resultSet.next()){
                       realPassword = resultSet.getString(1);
                       count++;
                    }
                    if(count > 1){
                        pw.println("This is very bad. The user name is not unique. How could this happened?");
                    }else{
                        //Загружаем java server page в зависимости от результата проверки пароля
                        if(userPassword.equals(realPassword)){
                            request.getRequestDispatcher("access_allowed.jsp").forward(request,response);
                        }else{
                            request.getRequestDispatcher("access_denied.jsp").forward(request,response);
                        }
                    }
                }catch(SQLException e){
                    pw.println("Some crap has happened while SQL connection");
                }
            } catch (ClassNotFoundException e) {
                pw.println("JDBC driver was not found");
            }
        }
    }
}
