package smart;

import java.util.List;

import xyz.onesway.bean.Temperature;
import xyz.onesway.dao.TemDao;

/**
 * @author Ben Li
 * @version Date：2015年6月1日 下午5:08:39
 */
public class Test004 {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        TemDao temDao = new TemDao();
        System.out.println(temDao.getNewTem("home1"));
//        List<Temperature> list = temDao.today("home1");
//        for (Temperature temperature : t) {
//            System.out.println(temperature.getTemperature());
//        }
//        int tem = 0;
//        int count = 1;
//        for(int j = 0; j < 24; j++) {
//            for (int i = 0; i < list.size(); i++) {
//                if(list.get(i).getHour() == j){
//                    count+=1;
//                    tem+=list.get(i).getTemperature();
//                }
//            }
//            System.out.println("时间:" + j +"点"+ ",个数：" + count +",累计:"+ tem +",均值:" + tem/count);
//            tem =0;
//            count =1;
//        }
    }

}
