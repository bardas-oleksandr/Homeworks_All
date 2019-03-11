package JavaUtil.CalendarClass;

import Casting.InstanceOf.C;
import Interfaces.IProblem;

import java.util.*;

public class Problem implements IProblem {
    @Override
    public void solve() {
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        System.out.println(calendar);
        System.out.println(date);
        System.out.println("Month: " + calendar.get(Calendar.MONTH));
        TimeZone timeZone = calendar.getTimeZone();
        System.out.println("timeZone " + timeZone);
        System.out.println("timeZone.getDisplayName() " + timeZone.getDisplayName());
        System.out.println("timeZone.getID() " + timeZone.getID());
        String[] all = TimeZone.getAvailableIDs();
        System.out.println("========================================");
        for(String line: all){
            System.out.println(line);
        }
        System.out.println("========================================");
        timeZone.setID(all[0]);
        System.out.println("timeZone.getID() " + timeZone.getID());

        calendar = new GregorianCalendar(2018,3,16);
        System.out.println(calendar.getTime());

        timeZone = calendar.getTimeZone();
        System.out.println(timeZone.getOffset(calendar.getTimeInMillis())/1000/60/60);

        long l = Calendar.getInstance().getTimeInMillis();
        Random rnd = new Random(l);
        System.out.println(rnd.nextInt());


    }
}
