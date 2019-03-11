package Simulators;
import Flowers.*;
import ShopExceptions.LogException;
import ShopExceptions.NoFlowersException;
import Store.FlowerStack;
import Vendor.IVendor;
import ShopExceptions.PriceException;
import java.util.Random;

public class SimulationVendor {
    public void makeBouquet(IVendor vendor) throws LogException{   //Цветы покупают букетами из нечетного количества цветов одного типа и цвета
        boolean flag = true;
        while(flag){    //Человек будет выбирать пока не выберет подходящий букет
            Random rnd = new Random();
            int max = vendor.getStore().getStacksCount();
            int stackIndex = rnd.nextInt(max);  //Моделируем случайный номер стэка (случайный тип и цвет цветов)
            int count = rnd.nextInt(6);
            count = count%2==0? count+1: count; //Пусть букеты будут только по случаю праздников
            FlowerStack stack = vendor.getStore().findStackByIndex(stackIndex);
            int restOfFlowers = stack.getCount();
            int number=0;
            Flower flower = stack.getFlower();
            String color = flower.getColor();
            try{
                if(restOfFlowers >= count){
                    if(flower instanceof Tulip){
                        number = 0;
                        for(int i = 0; i<count; i++){
                            vendor.addToBouquet(Tulip.class,color);
                        }
                    }else if(flower instanceof Carnation){
                        number = 1;
                        for(int i = 0; i<count; i++){
                            vendor.addToBouquet(Carnation.class,color);
                        }
                    }else if(flower instanceof RoseFlower){
                        number = 2;
                        for(int i = 0; i<count; i++){
                            vendor.addToBouquet(RoseFlower.class,color);
                        }
                    }else if(flower instanceof Gladiolus){
                        number = 3;
                        for(int i = 0; i<count; i++){
                            vendor.addToBouquet(Gladiolus.class,color);
                        }
                    }
                    flag = false;
                }
                else{   //Если цветы запрашиваемого типа есть в хранилище, но их недостаточно, бросаем исключение
                    switch(number){
                        case 0: throw new NoFlowersException(Tulip.class,color);
                        case 1: throw new NoFlowersException(Carnation.class,color);
                        case 2: throw new NoFlowersException(RoseFlower.class,color);
                        case 3: throw new NoFlowersException(Gladiolus.class,color);
                    }
                }
            }
            catch(PriceException e){
                System.out.println("\n====EXCEPTION====");
                System.out.println(e.getMessage());
                System.out.println("Details:");
                System.out.println("Type of flowers: " + e.getColor() + " " + e.getFlowerType().getName());
                System.out.println("Current price: " + e.getPrice() + "$");
                System.out.println("You can't sale these flowers because the price has not been established yet\n");
                flag = false;   //Для выхода из иммитатора поведения
                throw new LogException(e);
            }
            catch(NoFlowersException e){
                System.out.println("\n====EXCEPTION====");
                System.out.println(e.getMessage());
                throw new LogException(e);
            }
        }
    }

    //Старая версия метода public void makeBouquet. Покупатель просил товар "не глядя" на то что есть в магазине.
    public void exigentConsumer(IVendor vendor) throws LogException{   //Цветы покупают букетами из нечетного количества цветов одного типа и цвета
        boolean flag = true;
        while(flag){    //Человек будет выбирать пока не выберет подходящий букет
            Random rnd = new Random();
            int count = rnd.nextInt(6);
            count = count%2==0? count+1: count; //Пусть букеты будут только по случаю праздников
            int number = rnd.nextInt(6);    //Номер типа цветов
            String color = Interfaces.IConsole.generateColor();    //Цвет
            number = rnd.nextInt(4);
            int restOfFlowers = 0;  //Сюда мы запишем сколько еще в магазине осталось цветов, такого типа и ткого цвета, которые ищет покупатель
            try{
                switch(number){
                    case 0:
                        restOfFlowers=vendor.howMany(Tulip.class,color);
                        break;
                    case 1:
                        restOfFlowers=vendor.howMany(Carnation.class,color);
                        break;
                    case 2:
                        restOfFlowers=vendor.howMany(RoseFlower.class,color);
                        break;
                    case 3:
                        restOfFlowers=vendor.howMany(Gladiolus.class,color);
                        break;
                }
            }
            catch(NoFlowersException e){    //Если цветы запрашиваемого типа не найдены в хранилище
                System.out.println("\n====EXCEPTION====");
                System.out.println(e.getMessage());
                throw new LogException(e);
            }
            try{
                if(restOfFlowers >= count){
                    switch(number){
                        case 0:
                            for(int i = 0; i<count; i++){
                                vendor.addToBouquet(Tulip.class,color);
                            }
                            break;
                        case 1:
                            for(int i = 0; i<count; i++){
                                vendor.addToBouquet(Carnation.class,color);
                            }
                            break;
                        case 2:
                            for(int i = 0; i<count; i++){
                                vendor.addToBouquet(RoseFlower.class,color);
                            }
                            break;
                        case 3:
                            for(int i = 0; i<count; i++){
                                vendor.addToBouquet(Gladiolus.class,color);
                            }
                            break;
                    }
                    break;
                }
                else{   //Если цветы запрашиваемого типа есть в хранилище, но их недостаточно, все равно бросаем исключение
                    switch(number){
                        case 0: throw new NoFlowersException(Tulip.class,color);
                        case 1: throw new NoFlowersException(Carnation.class,color);
                        case 2: throw new NoFlowersException(RoseFlower.class,color);
                        case 3: throw new NoFlowersException(Gladiolus.class,color);
                    }
                }
            }
            catch(PriceException e){
                System.out.println("\n====EXCEPTION====");
                System.out.println(e.getMessage());
                System.out.println("Details:");
                System.out.println("Type of flowers: " + e.getColor() + " " + e.getFlowerType().getName());
                System.out.println("Current price: " + e.getPrice() + "$");
                System.out.println("You can't sale these flowers because the price has not been established yet\n");
                throw new LogException(e);
            }
            catch(NoFlowersException e){
                System.out.println("\n====EXCEPTION====");
                System.out.println(e.getMessage());
                throw new LogException(e);
            }
        }
        if(!flag){
            System.out.println(vendor.getBouquet());
            vendor.sellBouquet();
        }
    }
}
