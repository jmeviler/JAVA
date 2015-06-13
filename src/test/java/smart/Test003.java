package smart;

import java.util.Calendar;
import java.util.Date;

/**
 * @author Ben Li
 * @version Date：2015年5月29日 下午3:09:49
 */
public class Test003 {
    public static void main(String[] args) {
        Calendar now = Calendar.getInstance();  
        System.out.println("年: " + now.get(Calendar.YEAR));  
        System.out.println("月: " + (now.get(Calendar.MONTH) + 1) + "");  
        System.out.println("日: " + now.get(Calendar.DAY_OF_MONTH));  
        System.out.println("时: " + now.get(Calendar.HOUR_OF_DAY));  
        System.out.println("分: " + now.get(Calendar.MINUTE));  
        System.out.println("秒: " + now.get(Calendar.SECOND));  
    }
}
