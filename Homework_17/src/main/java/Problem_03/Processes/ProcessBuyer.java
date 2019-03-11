package Problem_03.Processes;

import Problem_03.Flowers.Flower;
import Problem_03.Interfaces.IProcess;
import Interfaces.IService;
import Problem_03.Shop.FlowerShop;
import Problem_03.ShopInterfaces.IBuyer;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class ProcessBuyer extends Process implements IProcess {
    private IBuyer buyer;

    public ProcessBuyer(FlowerShop shop) {
        this.buyer = shop;
    }

    @Override
    public void start() {
        int choice = 0;
        do {
            int typesCount = this.buyer.getReadyStacks();   //Получаем количество категорий цветов, готовых для продажи
            IService.clearConsole();
            System.out.println("Select type of flower");
            Map<Integer,List<Flower>> choiceMap = this.buyer.showForBuyer();
            System.out.println("0 - Exit");
            System.out.print("Your choice:");
            do {
                choice = IService.getInteger();
                if (choice < 0 || choice > typesCount) {
                    System.out.println("Incorrect input. Integer from the range 0.." + typesCount + " is expected.");
                    System.out.print("Try again: ");
                }
            } while (choice < 0 || choice > typesCount);
            if (choice > 0) {
                List<Flower> list = choiceMap.get(choice);
                if(list != null & list.size() > 0){
                    Flower flower = list.get(0);
                    Class type = flower.getClass();
                    String color = flower.getColor();
                    int count = 0;
                    int maxCount = this.buyer.howMany(type, color);
                    System.out.print("How many flowers do you want:");
                    do {
                        count = IService.getInteger();
                        if (count < 1 || count > maxCount) {
                            System.out.println("Incorrect input. Integer from the range 1.." + maxCount + " is expected.");
                            System.out.print("Try again: ");
                        }
                    } while (count < 1 || count > maxCount);
                    this.buyer.addToBouquet(type, color, count);
                    IService.clearConsole();
                    this.buyer.showForBuyer();
                    System.out.println("Do you want to buy the bouquet?");
                    System.out.println("1 - YES");
                    System.out.println("0 - NO");
                    System.out.print("Your answer:");
                    int answer = 0;
                    do {
                        answer = IService.getInteger();
                        if (answer != 0 && answer != 1) {
                            System.out.println("Incorrect input. 0 or 1 is expected.");
                            System.out.print("Try again: ");
                        }
                    } while (answer != 0 && answer != 1);
                    if (answer == 1) {
                        this.buyer.sellBouquet();
                        System.out.println("Congratulations! You have bought the bouquet.");
                    } else {
                        this.buyer.cleanBouquet();
                        System.out.println("Your purchase was canceled");
                    }
                    System.out.print("Press enter to continue");
                    try {
                        char str = (char) System.in.read();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }else{
                    System.out.println("You are not supposed to see it"); //Этого сообщение не должно никога появится
                }
            }
        } while (choice != 0);
    }
}
