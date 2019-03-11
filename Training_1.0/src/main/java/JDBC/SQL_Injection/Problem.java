package JDBC.SQL_Injection;

import Service.IProblem;

import java.sql.*;

//ЧТО ПОКАЗЫВАЕТ ПРИМЕР.
//Пример показывает что такое SQL injections и как с ними бороться
//Пароли не хранят в базах данных в том числе и из-за проблемы SQL injections
//Пароли хранят с использованием хеш-кода MD5
public class Problem implements IProblem {
    @Override
    public void solve() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            String url = "jdbc:mysql://localhost:1902/bigdatabase?useSSL=false";
            String userName = "root";
            String password = "root";

            try (Connection connection = DriverManager.getConnection(url, userName, password)) {

                //I. Проблема SQL injection
                String user_password = "1' OR '1'='1";//Если пользователь введет в качестве
                //пароля такое выражение, то операция SELECT вернет нам всех пользователей.
                //Все дело в том что выражение '1'='1' всегда истинно
                String query = "SELECT * FROM users WHERE password='" + user_password + "'";

                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                System.out.println("SQL-injection was successful");
                while(resultSet.next()){
                    System.out.print(resultSet.getString(1));
                    System.out.print("\t\tpassword: " + resultSet.getString(2));
                    System.out.println("\t\tage: " + resultSet.getInt(3));
                }

                //II. Как бороться в SQL injection
                //Надо использовать PreparedStatement
                query = "SELECT * FROM users WHERE password = ?";
                PreparedStatement pStatement = connection.prepareStatement(query);
                pStatement.setString(1, user_password); //При использовании PreparedStatement параметр
                //подставляется прямо поле, поэтому дописать что-либо к запросу невозможно в принципе
                resultSet = pStatement.executeQuery();
                System.out.println("SQL-injection was not successful");
                while(resultSet.next()){
                    System.out.print(resultSet.getString(1));
                    System.out.print("\t\tpassword: " + resultSet.getString(2));
                    System.out.println("\t\tage: " + resultSet.getInt(3));
                }
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Database access denied!");
            }
        } catch (ClassNotFoundException e) {
            System.out.println("MySQL driver was not found");
        }
    }
}
