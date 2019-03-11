package Problem_03.Processes;

import Problem_03.Shop.Product;
import Problem_03.ShopInterfaces.IProcess;
import Interfaces.IService;
import Problem_03.Shop.FlowerShop;
import Problem_03.Shop.ProductList;
import Problem_03.ShopInterfaces.IBuyer;

import java.util.Map;

public class ProcessBuyer extends Process implements IProcess {
    private IBuyer buyer;

    public ProcessBuyer(FlowerShop shop) {
        this.buyer = shop;
    }

    @Override
    public void start() {
        int choice;
        do {
            int typesCount = this.buyer.getAvailableCategories();   //Получаем количество категорий цветов, готовых для продажи
            IService.clearConsole();
            System.out.println("Select type of flower");
            Map<Integer,ProductList<Product>> choiceMap = this.buyer.showForBuyer();
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
                ProductList<Product> list = choiceMap.get(choice);
                if(list != null && !list.isEmpty()){
                    int count;
                    int maxCount = this.buyer.howMany(list.name(), list.color());
                    System.out.print("How many of " + list.color()+ " " + list.name() + "s do you want?:");
                    do {
                        count = IService.getInteger();
                        if (count < 1 || count > maxCount) {
                            System.out.println("Incorrect input. Integer from the range 1.." + maxCount + " is expected.");
                            System.out.print("Try again: ");
                        }
                    } while (count < 1 || count > maxCount);
                    this.buyer.addToPurchaseList(list.name(), list.color(), list.price(), count);
                    IService.clearConsole();
                    this.buyer.showForBuyer();
                    System.out.println("Do you want to buy the bouquet?");
                    System.out.println("1 - YES");
                    System.out.println("0 - NO");
                    System.out.print("Your answer:");
                    int answer;
                    do {
                        answer = IService.getInteger();
                        if (answer != 0 && answer != 1) {
                            System.out.println("Incorrect input. 0 or 1 is expected.");
                            System.out.print("Try again: ");
                        }
                    } while (answer != 0 && answer != 1);
                    if (answer == 1) {
                        this.buyer.buy();
                        System.out.println("Congratulations! You have bought the bouquet.");
                    } else {
                        this.buyer.cleanPurchaseList();
                        System.out.println("Your purchase was canceled");
                    }
                    IService.pressEnterToContinue();
                }else{
                    System.out.println("You are not supposed to see it"); //Этого сообщение не должно никога появится
                }
            }
        } while (choice != 0);
    }
}
