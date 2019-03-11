import Interfaces.IProblem;
import Interfaces.IService;

//import java.io.*;
//import java.nio.charset.Charset;
//import java.nio.charset.CharsetEncoder;
//import java.util.Properties;

public class Main {
    public static void main(String[] args) {

        //ПРИМЕР ПРОГРАММНОГО СОХРАНЕНИЯ ФАЙЛА СВОЙСТВ
//        Properties prop = new Properties();
//        prop.put("mainTitle","ВЫБЕРИТЕ РЕЖИМ ВХОДА");
//        prop.put("mode1","- Покупатель");
//        prop.put("mode2","- Собственник");
//        prop.put("mode3","- Поставщик");
//        prop.put("mode4","- Изменить язык");
//        prop.put("exit","- Выход");
//        prop.put("choice","Ваш выбор:");
//        prop.put("lang1","- английский");
//        prop.put("lang2","- украинский");
//        prop.put("lang3","- русский");
//
//        //Такой вывод запишет не ASCII-символы в формате \u0000, который нечитаемый при ручном открытии файла,
//        //но при чтении из файла средставми ResourceBundle, все будет ОК
//        try (OutputStream out = new FileOutputStream("src/main/resources/shopBundle_ru.properties")){
//            prop.store(out,"Русская версия");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        //Такой вывод запишет файл в формате, который можно будет прочитать при открытиии файла
//        //Но при чтении из файла с помощью средств ResourceBundle, мы получим нечитаемые иероглифы
//        try (Writer writer = new FileWriter("src/main/resources/shopBundle_ru.utf8")){
//            prop.store(writer,"Русская версия");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        int choice;
        final int PROBLEMS_COUNT = 3;
        do{
            IService.clearConsole();
            System.out.println("MENU");
            System.out.println("1 - FLOWERSTORE (problems #1 & #3)");
            System.out.println("2 - BASE64 (problem #2)");
            System.out.println("3 - SEARCHING ALL THE .TXT-FILES BY PATH (problem #4)");
            System.out.println("0 - EXIT");
            System.out.print("Your choice:");
            choice = IService.getIntegerBounded(0, PROBLEMS_COUNT);
            if(choice != 0){
                IProblem problem = Interfaces.ICreator.create(choice);
                problem.solve();
                IService.pressEnterToContinue();
            }
        }while(choice != 0);
        System.out.println("Buy-buy!");
    }
}
