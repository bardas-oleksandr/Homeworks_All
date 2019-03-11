package ua.levelup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service("dragon")
public class Dragon extends Monster {
    public Dragon(){
        super();
    }

    public Dragon(@Value("12")int power) {
        super(power);
        System.out.println("Dragon was created. Constructor with int parameter is called\n");
    }

    //Аннотацию @Autowired можно применить только к одному из конструкторов бина.
    //Экземпляр бина будет создан даже если его не запрашивать
    @Autowired
    public Dragon(@Value("12") String power){
        super(Integer.parseInt(power));
        System.out.println("Dragon was created. Constructor with String parameter is called\n");
    }

    @Override
    void doSomethingBad() {
        System.out.println("I am dragon. I will burn the village.\n");
    }
}
