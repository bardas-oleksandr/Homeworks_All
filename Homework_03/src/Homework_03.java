//Домашнее задание №3.
// Выполнил студент группы 17-5
// Бардась А.
public class Homework_03 {
    public static void main(String args[]){
        int number;
        do{
            ConsoleInterface.cleanConsole();
            Problem problem = null;
            System.out.println("Menu");
            System.out.println("1 - Problem # 1");
            System.out.println("2 - Problem # 2");
            System.out.println("3 - Problem # 3");
            System.out.println("0 - Exit");
            System.out.print("Your choice:");
            do{
                number = ConsoleInterface.getInteger();
                if(number < 0 || number > 3){
                    System.out.println("Incorrect input! Integer value from the range 0..3 is expected.");
                    System.out.print("Try again:");
                }
            }while(number < 0 || number > 3);
            switch(number)
            {
                case 1:
                    problem = new Problem_01();
                    break;
                case 2:
                    problem = new Problem_02();
                    break;
                case 3:
                    problem = new Problem_03();
                    break;
            }
            if(number != 0){
                problem.show();
            }
        }while(number != 0);
        System.out.print("Buy-buy!");
    }
}
