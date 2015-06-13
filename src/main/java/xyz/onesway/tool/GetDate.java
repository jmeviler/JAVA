package xyz.onesway.tool;

import java.util.Calendar;
import xyz.onesway.bean.Temperature;

/**
 * @author Ben Li
 * @version Date：2015年5月29日 下午3:13:49
 */
public class GetDate {
    static Calendar now = Calendar.getInstance();
    
    public static int getYear() {
        return now.get(Calendar.YEAR);
    }
    public static int getMonth(){
        return now.get(Calendar.MONTH ) + 1;
    }
    public static int getDay(){
        return now.get(Calendar.DAY_OF_MONTH);
    }
    public static int getHour(){
        return now.get(Calendar.HOUR_OF_DAY);
    }
    public static int getMinute() {
        return now.get(Calendar.MINUTE);
    }
    
    public static Temperature getTem(){
        Temperature temperature = new Temperature();
        temperature.setYear(getYear());
        temperature.setMonth(getMonth());
        temperature.setDay(getDay());
        temperature.setHour(getHour());
        temperature.setMinute(getMinute());
        return temperature;
    }
}
