//1. Есть конечный stream строк (предложений):
//1.1. Найти строку максимальной длины в потоке.
//1.2. Определите все символьные строки максимальной длины (если их несколько одинаковой длины)
//1.3. Определить сколько раз встречается нужное нам слово.

package Problem_01;

import Interfaces.IProblem;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.function.Function;
import java.util.stream.Stream;

public class Problem implements IProblem {
    @Override
    public void solve() {
        final String FILE = "src/main/resources/source.txt";
        //I. Looking for the longest line
        String theLongest = null;
        try (Stream<String> stream = Files.lines(Paths.get(FILE))) {  //Читаем файл как Stream<String>
            theLongest = stream.reduce("", (prev, next) -> {
                if (next.length() > prev.length()) {
                    prev = next;
                }
                return prev;
            });
            System.out.println("The longest line is:\n" + theLongest);
        } catch (NoSuchFileException e) {
            System.out.println("You need to have file src/main/resources/source.txt");
        } catch (IOException e) {
            System.out.println("IOException has happened");
        }

        //II. Collecting the ArrayList of the longest lines
        if (theLongest != null) {
            final int MAX = theLongest.length();
            try (Stream<String> stream = Files.lines(Paths.get(FILE))) {  //Читаем файл как Stream<String>
                System.out.println("The list of the longest lines: ");
                stream.filter((line) -> line.length() == MAX)
                        .forEach(System.out::println);
            } catch (NoSuchFileException e) {
                System.out.println("You need to have file src/main/resources/source.txt");
            } catch (IOException e) {
                System.out.println("IOException has happened");
            }
        }

        //II. Counting the number of the preset word occurrences
        final String WORD = "we";
        try (Stream<String> stream = Files.lines(Paths.get(FILE))) {
            Function<String, Integer> mapper = (line) -> {
                char[] spliterators = {' ', ',', '.', '!', '-', '?', ':', ';'}; //Массив символов-разделителей слов
                int count = 0;
                String current = line;
                int index = 0;
                //Цикл за каждый проход находит очередное вхождение искомого слова
                while ((index = current.toLowerCase().indexOf(WORD)) != -1) {
                    current = current.substring(index + WORD.length());
                    //Проверяем следующий символ, стоящий сразу после искомой комбинации символов.
                    //Если следующий символ является разделителем слова, значит мы нашли новое слово
                    boolean newWord = false;
                    for (char symbol:spliterators) {
                        if(current.indexOf(symbol) == 0){
                            newWord = true;
                            break;
                        }
                    }
                    if(newWord){
                        count++;
                    }
                }
                return count;
            };
            int count = stream.map(mapper).reduce(0, (a, b) -> a + b);
            System.out.println("Word \"" + WORD + "\" happens to occur " + count + " times in the whole file");
        } catch (NoSuchFileException e) {
            System.out.println("You need to have file src/main/resources/source.txt");
        } catch (IOException e) {
            System.out.println("IOException has happened");
        }
    }
}