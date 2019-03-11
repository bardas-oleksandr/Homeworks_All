package Collections.FromInterviews;

import Interfaces.IProblem;

import java.util.LinkedHashMap;
import java.util.Map;

public class Problem implements IProblem {
    @Override
    public void solve() {

        //Можно ли в Map положить значение для ключа key == null
        //Ответ: можно
        Map<Integer,String> map = new LinkedHashMap<>();
        map.put(null,"Hi");
        System.out.println(map.get(null));

        //Что будет если положить два значения для одного ключа?
        //Ответ: значения будут заменены
        map.put(null,"Hello");
        System.out.println("Size: " + map.size());


    }
}
