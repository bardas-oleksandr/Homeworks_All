package JDBC.PreparedStatement;

import Service.IProblem;

import java.sql.*;

public class Problem implements IProblem {
    @Override
    public void solve() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            String url = "jdbc:mysql://localhost:1902/europe?useSSL=false"; //Иногда при подключении выскакивает
            //предупреждение о использовании SSL протокола. Чтобы избавиться от проблемы, надо либо в свойствах
            //подключения в MySQL отключить SSL-протокол вручную, либо дописать в url символы useSSL=false
            String userName = "root";
            String password = "root";

            //Проблема 1. Долго не мог подключиться к базе. Надо было внимательно читать что пишет e.printStackTrace()
            //Оказалось что дравйвер не мог распознать TimeZone, в которой были символы из кириллицы.
            //Решение проблемы - в MySQL Workbench прописал такой запрос:
            //SET GLOBAL time_zone = '+3:00';
            //SELECT @@global.time_zone;
            //Проблема 2. Иногда в MySQL включена какая-то настройка, которая требует вход без использования пароля
            //В этой ситуации использование String password = ""; приводит к ошибке.
            //Решение проблемы - при вызове метода getConnection(url, userName, password) вместо password
            //надо просто прописать null - DriverManager.getConnection(url, userName, null))
            try (Connection connection = DriverManager.getConnection(url, userName, password)) {
                String query = "DELETE FROM countries WHERE country='Poland'";
                PreparedStatement statement = connection.prepareStatement(query);
                statement.execute();

                query = "INSERT INTO countries (country, population, area, capital) VALUES(?,?,?,?)";
                statement = connection.prepareStatement(query);
                statement.setString(1,"Poland");
                statement.setDouble(2,38);
                statement.setDouble(3,312);
                statement.setString(4,"Warsaw");
                statement.executeUpdate();

                query = "SELECT * FROM countries";
                statement = connection.prepareStatement(query);
                ResultSet resultSet = statement.executeQuery();
                while(resultSet.next()){
                    System.out.print(resultSet.getString(1));
                    System.out.print("\t\tpopulation: " + resultSet.getString(2));
                    System.out.print("\t\tarea: " + resultSet.getString(3));
                    System.out.println("\t\tcapital: " + resultSet.getString(4));
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
