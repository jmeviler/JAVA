package xyz.onesway.sever;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import at.ac.sbg.icts.spacebrew.client.*;

/**
 * @author Ben Li
 * @version Date：2015年5月25日 下午5:56:56
 */
public class YUN implements SpacebrewClientCallback{
    private final Logger log = LoggerFactory.getLogger(SpacebrewClient.class);
    private final SpacebrewClient client;

    public static void main(String[] args) {
        new YUN();
    }
    
    public YUN()
    {
        String serverUri = "ws://sandbox.spacebrew.cc:9000"; 
//        String serverUri = "ws://spacebrew.icts.sbg.ac.at:9000";
        client = new SpacebrewClient(this, serverUri, "YUN(Server)");

        client.addSubscriber("switch", SpacebrewMessage.TYPE_RANGE, "switchInput");
        client.addSubscriber("temperature", SpacebrewMessage.TYPE_RANGE, "temperatureInput");
        client.addSubscriber("time", SpacebrewMessage.TYPE_STRING, "timeInput");

        // add some subscribers
        client.addPublisher("switch", 100);
        client.addPublisher("temperature", 0);
        client.addPublisher("time", "");
        client.connect();
    }

    public void switchInput (int input) {
        if(input == 0 ){
            //todo false close
            System.out.println("YUN Close");
        }else if(input == 1){
            //todo true open
            System.out.println("YUN Open");
        }else {
            //none
            System.out.println("YUN Nothing");
        }
        client.publish("switch", input);
    }
    public void temperatureInput(int input) {
        log.info("The temperature is: " + input);
    }
    public void timeInput(String input) {
        log.info("The time is now: " + input);
    }
    
    public void onOpen() {
        log.info("Connection opened.");
        Thread thread = new Thread()
        {
            @Override
            public void run()
            {
                loop();
            }
        };
        thread.start();
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
//                booleanValue = !booleanValue;
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
