package JDBC.MySQL;

import Interfaces.IProblem;
import Interfaces.IService;

import java.io.IOException;
import java.sql.*;
import java.util.Scanner;

public class Problem implements IProblem {
    @Override
    public void solve() {
        final int EXIT_MODE = 0;
        final int ADD_MODE = 1;
        final int FIND_BY_NAME_MODE = 2;
        final int FIND_ALL_MODE = 3;
        try {
            Class.forName("org.mysql.Driver"); //Загружаем драйвер для работы с JDBC.

            //Параметры для соединения
            String login = "root";
            String password = "root";
            String url = "jdbc:mysql://localhost:1902/europe";

            //Создаем подключение. После завершения работы с подключением, его надо закрыть.
            //Интерфейс Connection implements AutoCloseable, поэтому можно использовать
            //try с ресурсами
            try (Connection connection = DriverManager.getConnection(url, login, password)) {
                int choice;
                do {
                    Interfaces.IService.clearConsole();
                    System.out.println("1 - Add species");
                    System.out.println("2 - Find species by English name");
                    System.out.println("3 - Find all species of the family");
                    System.out.println("0 - Exit");
                    System.out.print("Your choice:");
                    try {
                        choice = IService.getIntegerBounded(EXIT_MODE, FIND_ALL_MODE);
                        switch (choice) {
                            case ADD_MODE: {
                                Scanner scanner = new Scanner(System.in);

                                //Пользователь вводит данные, которые надо добавить в БД
                                System.out.print("Family:");
                                String family = scanner.nextLine();
                                System.out.print("Species:");
                                String species = scanner.nextLine();
                                System.out.print("English name:");
                                String name = scanner.nextLine();

                                //Создаем запрос
                                Statement statement = connection.createStatement();
                                String query = "INSERT INTO species (latin_name, english_name, family) VALUES ('" +
                                        species + "', '" +
                                        name + "', '" +
                                        family + "')";
                                statement.execute(query);
                                statement.close();
                            }
                            break;
                            case FIND_BY_NAME_MODE: {
                                //Пользователь задает английское наименование рыбы для поиска
                                System.out.print("English name:");
                                Scanner scanner = new Scanner(System.in);
                                String name = scanner.nextLine();

                                //Пример использования PreparedStatement. Форма запроса готовится один раз,
                                //далее можно просто менять параметры запроса и многократно повторять его
                                String query = "SELECT latin_name FROM species WHERE english_name = ?";
                                PreparedStatement statement = connection.prepareStatement(query);
                                //Задаем параметры запроса
                                statement.setString(1, name);
                                //Получаем результат запроса
                                ResultSet resultSet = statement.executeQuery();
                                //Читаем результат
                                while (resultSet.next()) {
                                    System.out.println(resultSet.getString(1));
                                }
                                statement.close();
                            }
                            break;
                            case FIND_ALL_MODE: {
                                //Пользователь задает семейство для поиска
                                System.out.print("Family:");
                                Scanner scanner = new Scanner(System.in);
                                String family = scanner.nextLine();
                                //Создаем запрос
                                Statement statement = connection.createStatement();
                                String query = "SELECT * FROM species WHERE family = '" + family + "'";
                                //Получаем результат
                                ResultSet resultSet = statement.executeQuery(query);

                                System.out.println("Family: " + family);
                                //Читаем результат
                                while (resultSet.next()) {
                                    System.out.println(resultSet.getString(1));
                                }
                                statement.close();
                            }
                            break;
                        }
                    } catch (IOException e) {
                        System.out.println("IOException has happened. Problem will be shutdown.");
                        choice = EXIT_MODE;
                    }
                    if (choice != EXIT_MODE) {
                        IService.pressEnterToContinue();
                    }
                } while (choice != EXIT_MODE);
            } catch (SQLException e) {
                System.out.println("Something bad has happened while connecting with SQL-database");
            }
        } catch (ClassNotFoundException e) {
            System.out.println("JDBC-driver was not found!");
        }
    }
}
