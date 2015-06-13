package smart;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import xyz.onesway.bean.Temperature;
import xyz.onesway.dao.TemDao;
import xyz.onesway.tool.GetDate;
import at.ac.sbg.icts.spacebrew.client.*;

/**
 * @author Ben Li
 * @version Date：2015年5月25日 下午5:56:56
 */
public class SpacebrewServiceServer implements SpacebrewClientCallback{
    private final Logger log = LoggerFactory.getLogger(SpacebrewClient.class);
    private final SpacebrewClient client;
    public static void main(String[] args) {
        new SpacebrewServiceServer();
    }
    
    public SpacebrewServiceServer()
    {
         String serverUri = "ws://sandbox.spacebrew.cc:9000";
        client = new SpacebrewClient(this, serverUri, "Server(mock)",
                "Server send and receiver");

        client.addSubscriber("switch", SpacebrewMessage.TYPE_BOOLEAN, "switchInput");
        client.addSubscriber("temperature", SpacebrewMessage.TYPE_RANGE, "temperatureInput");
        client.addSubscriber("time", SpacebrewMessage.TYPE_STRING, "timeInput");

        // add some subscribers
        client.addPublisher("switch", false);
        client.addPublisher("temperature", 0);
        client.addPublisher("time", "");
        client.connect();
    }
    
    public SpacebrewClient getClient () {
        return client;
    }
    
    public void switchInput (boolean input) {
        log.info("The switch turns: " + (input ? "on" : "off"));
    }
    public void temperatureInput(int input) {
        Temperature temperature = GetDate.getTem();
        temperature.setTemperature(input);
        TemDao temDao =  new TemDao();
        temDao.insert(temperature);
        log.info("The temperature is: " + input);
    }
    public void timeInput(String input) {
        log.info("The time is now: " + input);
    }
    
    public void setSwtich(boolean bool){
            client.publish("switch", bool);
    }
    
    public void onOpen() {
        log.info("Connection opened.");
    }

    public void onClose() {
        log.info("Connection closed.");
    }

    public void onError() {
        log.info("An error occured.");
    }
    
    private void loop(){
        boolean booleanValue = false;
        int rangeValue = 0;
        
        final double hertz = 1;
        final double NANO_SECOND = 1000000000;
        final double TIME_BETWEEN_UPDATES = NANO_SECOND / hertz;
        double now = System.nanoTime();
        double lastUpdateTime;
        
        while(true){
            lastUpdateTime = System.nanoTime();
            if(client.isConnected()){
                client.publish("switch", booleanValue);
                booleanValue = !booleanValue;
                client.publish("temperature", rangeValue);
                rangeValue++;
                if(rangeValue >100){
                    rangeValue = 0;
                }
                String time = System.currentTimeMillis() + "";
                client.publish("time", time);
            }
            
            while (now - lastUpdateTime < TIME_BETWEEN_UPDATES)
            {
                Thread.yield();
                try
                {
                    Thread.sleep(1);
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
                now = System.nanoTime();
            }

        }
    }
}
