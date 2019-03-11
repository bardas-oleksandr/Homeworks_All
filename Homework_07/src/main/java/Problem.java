//1. Введите в наш цветочный киоск интерфейсы для работы продавца (может только формировать букеты и продавать их),
// интерфейс владельца(устанавливает цену на цветок) и интерфейс поставщика (доставляет определенное количество цветов
// определенного вида по определенной стоимости). Реализуйте класс FlowerShop, который имплементирует методы всех
// интерфейсов. В основном классе Main съимитируйте поставку товара, его оценку, рэндомную генерацию букетов и их
// распродажу до конца. Подсчитайте сумму прибыли (выторг - себестоимость)

import Owner.IOwner;
import Provider.IProvider;
import ShopExceptions.LogException;
import Simulators.SimulationVendor;
import Simulators.SimulatorOwner;
import Simulators.SimulatorProvider;
import Vendor.IVendor;

public class Problem implements Interfaces.IProblem {
    public void solve(){
        //Let's create flower shop and plug-in interfaces
        IOwner owner;
        IProvider provider;
        IVendor vendor;
        {
            FlowerShop flowerShop = new FlowerShop();   //Указатель на FlowerShop будет утерян после выхода из блока {}, но останется доступ через интерефейсы
            owner = flowerShop;
            provider = flowerShop;
            vendor = flowerShop;
        }
        //Создадим логгер
        Logger logger = new Logger();

        //Создаем симуляторы поведения для каждого из участников процесса
        SimulatorProvider sProvider = new SimulatorProvider();
        SimulatorOwner sOwner = new SimulatorOwner();
        SimulationVendor sVendor = new SimulationVendor();

        //Моделируем поставку партий цветов
        System.out.println("\n\n======================Provider delivers flowers=====================================");
        //ТУТ БУДЕТ ИСКЛЮЧЕНИЕ. Попытка установить отрицательную цену поставщика
        try{
            sProvider.tryNegativeCostPrice(provider);
        }
        catch(LogException ex){
            logger.addLog(ex.getCause().toString());
        }

        //ТУТ БУДЕТ ИСКЛЮЧЕНИЕ. Попытка поставить отрицательное количество цветов
        try{
            sProvider.tryNegativeCount(provider);
        }
        catch(LogException ex){
            logger.addLog(ex.getCause().toString());
        }

        //Моделируем поставку 5 партий цветов (тут без ошибок и исключений)
        for(int i =0; i<5; i++){
            try{
                sProvider.generate(provider);
            }
            catch(LogException ex){
                logger.addLog(ex.getCause().toString());
            }
        }
        System.out.println("\n\n====================Situation after the flowers were delivered======================");
        owner.showAll();

        //ТУТ БУДЕТ ИСКЛЮЧЕНИЕ. Моделируем попытку купить цветы до установления цены
        System.out.println("\n\n======================Vendor is trying to sale flowers with undefined price===================");
        if(vendor.isEmpty() == false){
            try{
                sVendor.exigentConsumer(vendor);
            }
            catch(LogException ex){
                logger.addLog(ex.getCause().toString());
            }
            owner.showAll();
        }

        //Задаем цену на цветы
        System.out.println("\n\n======================Owner is trying to set unacceptable price====================================");
        //ТУТ БУДЕТ ИСКЛЮЧЕНИЕ. Пытаемся задать отрицательную цену
        try{
            sOwner.tryNegativePrice(owner);
        }
        catch(LogException ex){
            logger.addLog(ex.getCause().toString());
        }
        owner.showAll();

        //Устанавливаем допустимые цены
        System.out.println("\n\n======================Owner is setting the price====================================");
        try{
            sOwner.generatePrice(owner);
        }
        catch(LogException ex){
            logger.addLog(ex.getCause().toString());
        }
        owner.showAll();

        //Моделируем распродажу цветов
        System.out.println("\n\n======================Vendor is saling flowers======================================");
        int counter = 1;
        while(vendor.isEmpty() == false){
            System.out.println("\n\n======================Request #" + counter++ + "===============================================");
            try{
                sVendor.makeBouquet(vendor);
                System.out.println(vendor.getBouquet());
                vendor.sellBouquet();
            }
            catch(LogException ex){
                logger.addLog(ex.getCause().toString());
            }
        }
        if(vendor.isEmpty()){
            try{
                throw new LogException(new LogException("Store is empty"));
            }
            catch(LogException ex){
                logger.addLog(ex.getCause().toString());
            }
        }

        //Смотрим журнал логов
        System.out.println("\n\n======================Watching logs====================================");
        logger.print();
    }
}
