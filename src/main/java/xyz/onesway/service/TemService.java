package xyz.onesway.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import at.ac.sbg.icts.spacebrew.client.RangeSubscriber;
import at.ac.sbg.icts.spacebrew.client.SpacebrewClient;
import at.ac.sbg.icts.spacebrew.client.SpacebrewClientCallback;
import at.ac.sbg.icts.spacebrew.client.SpacebrewMessage;
import xyz.onesway.bean.Temperature;
import xyz.onesway.dao.TemDao;

/**
 * @author Ben Li
 * @version Date：2015年5月29日 下午3:03:13
 */
@Service
public class TemService implements SpacebrewClientCallback{
    private final Logger log = LoggerFactory.getLogger(SpacebrewClient.class);
    @Autowired
    private TemDao temDao;
    private int temperature;
    private final SpacebrewClient client;
    
    public static void main(String[] args) {
        new TemService();
    }
    
    public void add(Temperature temperature) {
        try {
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public class TempRangeSubscriber implements RangeSubscriber
    {
        public TempRangeSubscriber()
        {

        }

        public void receive(int value)
        {
            log.warn("received temperature:"+value);
            temperature = value;
            Temperature tem = new Temperature();
        }
    }
    
    public TemService() {
        temDao = new TemDao();
        String serverUri = "ws://spacebrew.icts.sbg.ac.at:9000";
        client = new SpacebrewClient(this, serverUri, "Server(mock)",
                "Server send and receiver");
        client.addSubscriber("test", SpacebrewMessage.TYPE_RANGE, "testInput");
        client.connect();
    }
    
//    public void test() {
//        Temperature tem= new Temperature();
//        tem.setTemperature(temperature);
//        temDao.save(tem);
//    }
    
//    public void testInput(int input) {
//        log.info("The test is: " + input);
//        System.out.println(temDao);
//    }
    
    public void onOpen() {
        // TODO Auto-generated method stub
        TempRangeSubscriber tempRangeSubscriber = new TempRangeSubscriber();
        client.addSubscriber("temperatureSubscriber", tempRangeSubscriber);
    }

    public void onClose() {
        // TODO Auto-generated method stub
        
    }

    public void onError() {
        // TODO Auto-generated method stub
        
    }
}
