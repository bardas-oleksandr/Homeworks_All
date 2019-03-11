package Simulators;
import Flowers.Carnation;
import Flowers.Gladiolus;
import Flowers.RoseFlower;
import Flowers.Tulip;
import Vendor.IVendor;
import java.util.Random;
public class SimulationVendor {
    public void makeBouquet(IVendor vendor){   //Цветы покупают букетами из нечетного количества цветов одного типа и цвета
        boolean flag = true;
        while(flag){    //Человек будет выбирать пока не выберет подходящий букет
            Random rnd = new Random();
            int count = rnd.nextInt(6);
            count = count%2==0? count+1: count; //Пусть букеты будут только по случаю праздников
            int number = rnd.nextInt(6);    //Номер типа цветов
            String color = null;    //Цвет
            switch(number){
                case 0:
                    color = "yellow";
                    break;
                case 1:
                    color = "red";
                    break;
                case 2:
                    color = "white";
                    break;
                case 3:
                    color = "orange";
                    break;
                case 4:
                    color = "blue";
                    break;
                case 5:
                    color = "purple";
                    break;
                default:
                    color = "cyan";
            }
            number = rnd.nextInt(4);
            int restOfFlowers = 0;  //Сюда мы запишем сколько еще в магазине осталось цветов, такого типа и ткого цвета, которые ищет покупатель
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
                flag = false;
            }
        }
    }
}
