package JavaUtil.FormatterScanner;

import Interfaces.IProblem;

import java.util.Formatter;

public class Problem implements IProblem {
    @Override
    public void solve() {
        double value = 12.34567898765432;
        Formatter formatter = new Formatter(System.out);
        formatter.format("Formatting in %s is very simple: %d, %f\n", "Java", 10, value);

        formatter = new Formatter();
        value = 12.12345;
        formatter.format("|%+05d|\n|%+12.2f|\n", 10, value); //%+12.2f - означает вещественное число,
        // минимальная ширина поля 12, число знаков после запятой 2. Если положительное - выведется с плюсом
        String str = formatter.toString();
        System.out.println(str);

        formatter.format("|%-12.2f|\n|%12.2f|", value, value);  //Выравнивание по левому краю, потом по правому
        str = formatter.toString();
        System.out.println(str);

    }
}
