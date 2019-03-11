package Problems_01_and_03.Processes;

import Problems_01_and_03.Shop.Product;
import Problems_01_and_03.Shop.ShopBundle;
import Problems_01_and_03.ShopInterfaces.IProcess;
import Interfaces.IService;
import Problems_01_and_03.Shop.FlowerShop;
import Problems_01_and_03.Shop.ProductList;
import Problems_01_and_03.ShopInterfaces.IBuyer;

import java.util.Map;

public class ProcessBuyer extends Process implements IProcess {
    private IBuyer buyer;

    public ProcessBuyer(FlowerShop shop) {
        this.buyer = shop;
    }

    @Override
    public void start() {
        final int EXIT = 0;
        final int YES = 1;
        final int NO = 0;
        int choice;
        do {
            int typesCount = this.buyer.getAvailableCategories();   //Получаем количество категорий цветов, готовых для продажи
            IService.clearConsole();
            System.out.println(ShopBundle.getString(ShopBundle.SELECT_TYPE));
            Map<Integer,ProductList<Product>> choiceMap = this.buyer.showForBuyer();
            System.out.println(EXIT + ShopBundle.getString(ShopBundle.EXIT_MODE));
            System.out.print(ShopBundle.getString(ShopBundle.CHOICE));
            do {
                choice = IService.getInteger();
                if (choice < 0 || choice > typesCount) {
                    System.out.println("Incorrect input. Integer from the range 0.." + typesCount + " is expected.");
                    System.out.print(ShopBundle.getString(ShopBundle.TRY_AGAIN));
                }
            } while (choice < 0 || choice > typesCount);
            if (choice > 0) {
                ProductList<Product> list = choiceMap.get(choice);
                if(list != null && !list.isEmpty()){
                    int count;
                    int maxCount = this.buyer.howMany(list.name(), list.color());
                    System.out.print(ShopBundle.getString(ShopBundle.HOW_MANY));
                    do {
                        count = IService.getInteger();
                        if (count < 1 || count > maxCount) {
                            System.out.println("Incorrect input. Integer from the range 1.." + maxCount + " is expected.");
                            System.out.print(ShopBundle.getString(ShopBundle.TRY_AGAIN));
                        }
                    } while (count < 1 || count > maxCount);
                    this.buyer.addToPurchaseList(list.name(), list.color(), list.price(), count);
                    IService.clearConsole();
                    this.buyer.showForBuyer();
                    System.out.println(ShopBundle.getString(ShopBundle.FINAL_AGREEMENT));
                    System.out.println(YES + " " + ShopBundle.getString(ShopBundle.YES_CHOICE));
                    System.out.println(NO + " " + ShopBundle.getString(ShopBundle.NO_CHOICE));
                    System.out.print(ShopBundle.getString(ShopBundle.CHOICE));
                    int answer;
                    do {
                        answer = IService.getInteger();
                        if (answer != 0 && answer != 1) {
                            System.out.println("Incorrect input. 0 or 1 is expected.");
                            System.out.print(ShopBundle.getString(ShopBundle.TRY_AGAIN));
                        }
                    } while (answer != 0 && answer != 1);
                    if (answer == 1) {
                        this.buyer.buy();
                        System.out.println(ShopBundle.getString(ShopBundle.CONGRATULATIONS));
                    } else {
                        this.buyer.cleanPurchaseList();
                        System.out.println(ShopBundle.getString(ShopBundle.PURCHASE_CANCEL));
                    }
                    IService.pressEnterToContinue();
                }else{
                    System.out.println("You are not supposed to see it"); //Этого сообщение не должно никога появится
                }
            }
        } while (choice != 0);
    }
}
