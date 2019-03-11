package JDBC.BLOB;

import Service.IProblem;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.sql.*;

public class Problem implements IProblem {
    @Override
    public void solve() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:1902/blob_database?useSSL=false";
            String userName = "root";
            String password = "root";

            try (Connection connection = DriverManager.getConnection(url, userName, password)) {
                //1. Как это делаю я.
                String query = "INSERT INTO users (user, age, photo) VALUES (?,?,?)";
                PreparedStatement pStatement = connection.prepareStatement(query);
                pStatement.setString(1,"Stark");
                pStatement.setInt(2,43);
                try(FileInputStream input = new FileInputStream("src/main/resources/Stark.jpg")){
                    pStatement.setBlob(3, input);
                    pStatement.execute();
                }catch(IOException e){
                    e.printStackTrace();
                }

                query = "SELECT * FROM users WHERE user = 'Stark'";
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                while (resultSet.next()) {
                    System.out.print(resultSet.getString(1));
                    System.out.println("\t\tage: " + resultSet.getInt(2));
                    Blob blob = resultSet.getBlob(3);
                    InputStream inputStream = blob.getBinaryStream();

                    //Следующие два блока кода абсолютно не нужны для понимания примера.
                    //Тут я просто вспоминал некоторые вопросы
                    //Скопируем картинку из базы данных в файл
                    try (BufferedOutputStream out = new BufferedOutputStream(
                            new FileOutputStream("_copy.jpg"))) {
                        final int SIZE = 1000;
                        byte[] arr = new byte[SIZE];
                        int count = 0;
                        while ((count = inputStream.read(arr)) > 0) {
                            out.write(arr, 0, count);
                        }
                        ;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    //Покажем скопированный файл
                    try {
                        File file = new File("_copy.jpg");
                        new ProcessBuilder("mspaint", file.getAbsolutePath()).start().waitFor();
                        System.out.println("And now we are moving forward");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                //2. Как это делает хуй с ютуба
                try {
                    //Отдельно готовит Blob
                    BufferedImage image = ImageIO.read(new File("src/main/resources/Ronan.jpg"));
                    Blob blob = connection.createBlob();
                    OutputStream outputStream = blob.setBinaryStream(1);
                    ImageIO.write(image, "jpg", outputStream);

                    //Дальше работает стандартным образом
                    String otherQuery = "INSERT INTO users (user, age, photo) VALUES (?,?,?)";
                    PreparedStatement pStatement1 = connection.prepareStatement(otherQuery);
                    pStatement1.setString(1, "Ronan");
                    pStatement1.setInt(2, 73);
                    pStatement1.setBlob(3, blob);
                    pStatement1.execute();

                    query = "SELECT * FROM users WHERE user = 'Ronan'";
                    statement = connection.createStatement();
                    resultSet = statement.executeQuery(query);
                    while (resultSet.next()) {
                        System.out.print(resultSet.getString(1));
                        System.out.println("\t\tage: " + resultSet.getInt(2));
                        Blob blob1 = resultSet.getBlob(3);
                        InputStream inputStream = blob1.getBinaryStream();
                        BufferedImage image1 = ImageIO.read(inputStream);
                        File file = new File("_saved.jpg");
                        ImageIO.write(image,"jpg", file);
                    }

                    pStatement1.close();
                    pStatement.close();
                    statement.close();
                } catch (IOException e) {
                    e.printStackTrace();
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
