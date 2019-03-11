package Problem_03;

import java.io.IOException;

//3) Eсть класс Mail (письмо) в котором есть 3 характеристики - head, body и footer. И метод writeMail -> который
// печатает письмо, состоящее из этих характеристик. Создать механизм, который позволит нам переопределять body письма,
// например для разных праздников. (Хочу чтобы вы расмотрели эту задачу с точки зрения паттерна Декоратор)
public class Problem_03 implements Interfaces.IProblem{
    public void solve() throws IOException{
        Mail mail = new Mail(new StringBuilder("Dear John!\n\n"),
                new StringBuilder("Wish you good luck!\n\n"),
                new StringBuilder("Sincerely yours, Bob!\n"));

        System.out.println("\n--------------Without decorator---------------------\n");
        System.out.print(mail.writeMail()); //Письмо без декоратора

        System.out.println("\n--------------With Birtday decorator---------------------\n");
        Dec decorator = new DecBirthday(mail);
        System.out.print(decorator.writeMail()); //Письмо с декоратором День рождения

        System.out.println("\n--------------With New Year decorator---------------------\n");
        decorator = new DecNewYear(mail);
        System.out.print(decorator.writeMail()); //Письмо с декоратором Новый год

        System.out.println("\n--------------With two decorators---------------------\n");
        decorator = new DecBirthday(decorator);
        System.out.println(decorator.writeMail()); //Письмо с двумя декоратороми
    }
}
