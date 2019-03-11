package InterfacesProblems.ComparableVSComparator;

import Interfaces.IProblem;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;

public class Problem implements IProblem {
    @Override
    public void solve() {
        Fish pike = new Fish("pike", 3);
        Fish zander = new Fish("zander", 2);

        //Comparable удобно использовать для естественных способов сравнения
        //Например, для рыбы - по весу
        if(pike.compareTo(zander) > 0){
            System.out.println(pike + " is bigger then " + zander);
        }else{
            System.out.println(pike + " is smaller then " + zander);
        }

        //Comparator удобно использовать для нестандартных способ сравнения
        //Например, любого судака будем считать "важнее" любой щуки
        Comparator<Fish> comparator = (x,y)->{
            Map<String,Integer> map = new LinkedHashMap<>();
            map.put("pike",3);
            map.put("zander",4);
            map.put("catfish",5);
            return map.get(x.name()) - map.get(y.name());
        };
        if(pike.useComparator(comparator, zander) > 0){
            System.out.println(pike + " is more important then " + zander);
        }else{
            System.out.println(pike + " is less important then " + zander);
        }

        //Кроме того, Comparator позволяет создавать систему последовательных критериев,
        //используя метод thenComparing
        //Например - сначала сравниваем вид рыбы, и если он равен - сравниваем вес
        comparator = comparator.thenComparing((x)->x.weight());
        Fish smallPike = new Fish("pike", 1);
        if(pike.useComparator(comparator, smallPike) > 0){
            System.out.println(pike + " is more important then " + smallPike);
        }else if(pike.useComparator(comparator, smallPike) < 0){
            System.out.println(pike + " is less important then " + smallPike);
        }else{
            System.out.println("Fish are equal");
        }
    }
}
