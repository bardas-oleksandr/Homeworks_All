package JDBC.StoredProcedure;

import Service.IProblem;

import java.sql.*;

public class Problem implements IProblem {
    @Override
    public void solve() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String user = "root";
            String password = "root";
            String url = "jdbc:mysql://localhost:1902/bigdatabase?useSSL=false";
            try {
                Connection connection = DriverManager.getConnection(url,user,password);

                //I. Процедура, возвращающая значение
                String sql = "{CALL getPassword(?)}";   //getPassword - имя процедуры,
                //созданной прямо в MySQL или PostgreSQL
                CallableStatement statement = connection.prepareCall(sql);
                statement.registerOutParameter(1, Types.VARCHAR);
                statement.execute();
                System.out.println("Password: " + statement.getString(2));

                //II. Процедура, выполняющая запрос. Результат получаем через ResultSet
                statement = connection.prepareCall(sql);
                statement.setString(1,"bardas");
                ResultSet resultSet = statement.executeQuery();
                while(resultSet.next()){
                    System.out.println("Password: " + resultSet.getString(2));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
