package ua.levelup.aspectj;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.DeclareParents;

//Для внедрения новых интерфейсов в бины через аннотации,
//приходится создавать дополнительный класс
@Aspect
public class SingerIntroducer {
    //В параметрах аннотации не указывается implement-interface
    //так как тип интерфейса определяется типлм поля, которое
    //аннотируется
    @DeclareParents(value = "ua.levelup.aspectj.Dragon+",
            defaultImpl=ua.levelup.aspectj.CoolSinger.class)
    public static Singer singer;
}
