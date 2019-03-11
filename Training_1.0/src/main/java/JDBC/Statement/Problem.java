package JDBC.Statement;

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
            try(Connection connection = DriverManager.getConnection(url, userName, password)) {
                if(!connection.isClosed()){
                    System.out.println("We have established connection with database");
                }

                Statement statement = connection.createStatement();

                statement.execute("DELETE FROM countries WHERE country='Ukraine'");

                ResultSet resultSet = statement.executeQuery("SELECT * FROM countries");
                while(resultSet.next()){
                    System.out.print(resultSet.getString(1));
                    System.out.print("\t\tpopulation: " + resultSet.getString("population"));   //Можно обращаться по имени поля
                    System.out.print("\t\tarea: " + resultSet.getString(3));
                    System.out.println("\t\tcapital: " + resultSet.getString(4));
                }

                try{
                    //Метод executeUpdate возвращает количество измененных записей
                    int count = statement.executeUpdate("INSERT INTO countries (country, population, area, capital) VALUES ('Ukraine', 43, 602, 'Kyiv')");
                    System.out.println("Updated " + count + " lines");
                }catch(SQLIntegrityConstraintViolationException e){
                    System.out.println("Attempt to dublicate the primary key");
                }

                System.out.println("AFTER INSERTION");
                resultSet = statement.executeQuery("SELECT * FROM countries");
                while(resultSet.next()){
                    System.out.print(resultSet.getString(1));
                    System.out.print("\t\tpopulation: " + resultSet.getString(2));
                    System.out.print("\t\tarea: " + resultSet.getString(3));
                    System.out.println("\t\tcapital: " + resultSet.getString(4));
                }

                int count = statement.executeUpdate("UPDATE countries SET area=603.5, population=42.2 WHERE country='Ukraine'");
                System.out.println("Updated " + count + " lines");
                resultSet = statement.executeQuery("SELECT * FROM countries");
                System.out.println("AFTER UPDATING");
                while(resultSet.next()){
                    System.out.print(resultSet.getString(1));
                    System.out.print("\t\tpopulation: " + resultSet.getString(2));
                    System.out.print("\t\tarea: " + resultSet.getString(3));
                    System.out.println("\t\tcapital: " + resultSet.getString(4));
                }

                statement.addBatch("INSERT INTO countries (country, population, area, capital) VALUES ('France', 66, 547, 'Paris')");
                statement.addBatch("INSERT INTO countries (country, population, area, capital) VALUES ('Spain', 46, 505, 'Madrid')");
                statement.executeBatch();
                statement.clearBatch(); //Сомнительно что это надо делать, так все работает правильно и без этого метода

                System.out.println("AFTER EXECUTEBATCH");
                resultSet = statement.executeQuery("SELECT * FROM countries");
                while(resultSet.next()){
                    System.out.print(resultSet.getString(1));
                    System.out.print("\t\tpopulation: " + resultSet.getString(2));
                    System.out.print("\t\tarea: " + resultSet.getString(3));
                    System.out.println("\t\tcapital: " + resultSet.getString(4));
                }

                statement.addBatch("DELETE FROM countries WHERE country='France'");
                statement.addBatch("DELETE FROM countries WHERE country='Spain'");
                statement.executeBatch();
                statement.clearBatch();

                System.out.println("AFTER DELETING");
                resultSet = statement.executeQuery("SELECT * FROM countries");
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
