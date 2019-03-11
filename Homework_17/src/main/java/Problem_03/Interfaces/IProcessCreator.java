package Problem_03.Interfaces;

import Problem_03.Processes.ProcessBuyer;
import Problem_03.Processes.ProcessOwner;
import Problem_03.Processes.ProcessProvider;
import Problem_03.Shop.FlowerShop;

public interface IProcessCreator {
    static IProcess create(int choice, FlowerShop shop) throws IllegalStateException{
        switch(choice){
            case 1: return new ProcessBuyer(shop);
            case 2: return new ProcessOwner(shop);
            case 3: return new ProcessProvider(shop);
            default: throw new IllegalArgumentException();
        }
    }
}
