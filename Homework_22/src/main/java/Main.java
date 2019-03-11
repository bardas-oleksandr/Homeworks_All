import Interfaces.IService;

public class Main {
    public static void main(String[] args) {
        int choice;
        final int PROBLEMS_COUNT = 1;
        do {
            IService.clearConsole();
            System.out.println("MENU");
            System.out.println("1 - Calculating array sum with Callable");
            System.out.println("0 - EXIT");
            System.out.print("Your choice:");
            choice = IService.getIntegerBounded(0, PROBLEMS_COUNT);
            if (choice != 0) {
                try{
                    Interfaces.ICreator.create(choice).solve();
                }catch(IllegalArgumentException e){
                    System.out.println("Selected item can't be created");
                }
                IService.pressEnterToContinue();
            }
        } while (choice != 0);
        System.out.println("Buy-buy!");
    }
}
