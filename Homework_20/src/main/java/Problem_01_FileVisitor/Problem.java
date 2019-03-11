package Problem_01_FileVisitor;

import Interfaces.IProblem;
import Interfaces.IService;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Formatter;
import java.util.Properties;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Problem implements IProblem {
    private Properties properties;
    private final String DEFAULT_SEARCH = "defaultSearch";
    private final String SEARCH_RESULTS = "searchResults";

    public Problem() {
        this.properties = new Properties();
        try (InputStream stream = new FileInputStream("src/main/resources/Problem_Searching/paths.properties")) {
            properties.load(stream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void solve() {
        int choice;
        final int COUNT = 3;
        do {
            IService.clearConsole();
            System.out.println("MENU");
            System.out.println("1 - Set path for exploreSearching");
            System.out.println("2 - Use default path");
            System.out.println("3 - Show searching results");
            System.out.println("0 - Exit");
            System.out.print("Your choice:");
            choice = IService.getIntegerBounded(0, COUNT);
            String path = null;
            switch (choice) {
                case 1:
                    System.out.print("Set the path: ");
                    path = new Scanner(System.in).nextLine();
                    break;
                case 2:
                    path = properties.getProperty(DEFAULT_SEARCH);
                    break;
                case 3:
                    showResults();
                    break;
            }
            if (path != null) {
                File file = new File(path);
                System.out.print("Text to find:");
                String text = new Scanner(System.in).nextLine();
                exploreSearching(file, text);  //Процедура исследования эффективности многопоточного поиска
            }
            if (choice != 0) {
                IService.pressEnterToContinue();
            }
        } while (choice != 0);
    }

    //Процедура исследования эффективности многопоточного поиска методом searchInFile
    private void exploreSearching(File file, String text) {
        final int SERIES_COUNT = 10;
        final int MIN = 1;
        final int MAX = 30;
        final int DELTA = 3;
        int count = (MAX - MIN) / DELTA + 1;
        long[][] results = new long[count][SERIES_COUNT];
        clearFile();
        //Создадим поток для сохранения результатов поиска
        try (OutputStream output = new FileOutputStream(this.properties.getProperty(SEARCH_RESULTS), true)) {
            SynchronizedOutputStream sout = new SynchronizedOutputStream(output);
            //Цикл по количеству серий экспериментов
            for (int j = 0; j < SERIES_COUNT; j++) {
                //Цикл по количеству экспериментов в серии.
                //Каждый эксперимент соответсвует определенному значению количества потоков
                for (int i = 0; i < count; i++) {
                    results[i][j] -= System.currentTimeMillis();
                    ExecutorService executor = Executors.newFixedThreadPool(MIN + i * DELTA);
                    String line = "SEARCHING RESULTS FOR THREADS COUNT = " + (MIN + i * DELTA);
                    sout.write(line.getBytes());
                    Files.walkFileTree(Paths.get(file.getAbsolutePath()),
                            new SearcherFileVisitor(executor, text, sout));
                    executor.shutdown();
                    try {
                        executor.awaitTermination(24L, TimeUnit.HOURS);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    results[i][j] += System.currentTimeMillis();
                }
            }
            System.out.println("===================Efficiency results===============================================");
            for (int i = 0; i < count; i++) {
                System.out.print((MIN + i * DELTA) + " - \t\t");
                Formatter formatter = new Formatter();
                long sum = 0;
                for (int j = 0; j < SERIES_COUNT; j++) {
                    formatter.format("%07d\t", results[i][j]);
                    sum += results[i][j];
                }
                formatter.format("mean: %07d\n", sum / SERIES_COUNT);
                System.out.print(formatter);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void clearFile() {
        try (OutputStream output = new FileOutputStream(this.properties.getProperty(SEARCH_RESULTS))) {
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showResults() {
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(this.properties.getProperty(SEARCH_RESULTS)), StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
