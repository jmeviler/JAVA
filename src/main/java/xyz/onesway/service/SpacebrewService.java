package xyz.onesway.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xyz.onesway.bean.Temperature;
import xyz.onesway.dao.TemDao;
import xyz.onesway.tool.GetDate;
import at.ac.sbg.icts.spacebrew.client.*;

/**
 * @author Ben Li
 * @version Date：2015年5月25日 下午5:56:56
 */

@Service
public class SpacebrewService implements SpacebrewClientCallback{
    private final Logger log = LoggerFactory.getLogger(SpacebrewClient.class);

    private final SpacebrewClient client;

    public SpacebrewService()
    {
         String serverUri = "ws://spacebrew.icts.sbg.ac.at:9000";
        client = new SpacebrewClient(this, serverUri, "Smart Home Client",
                "WEB Client send and receiver");
        client.addSubscriber("switch", SpacebrewMessage.TYPE_BOOLEAN, "switchInput");
        client.addSubscriber("temperature", SpacebrewMessage.TYPE_RANGE, "temperatureInput");
        client.addSubscriber("time", SpacebrewMessage.TYPE_STRING, "timeInput");

        // add some subscribers
        client.addPublisher("switch", false);
        client.addPublisher("temperature", 0);
        client.addPublisher("time", "");
        client.connect();
    }
    
    public void switchInput (boolean input) {
        log.info("The switch turns: " + (input ? "on" : "off"));
    }
    public void temperatureInput(int input) {
        Temperature temperature = new Temperature();//Temperature is a entity
        temperature.setTemperature(input);
        TemDao temDao =  new TemDao();
        temDao.insert(temperature);
        log.info("The temperature is: " + input);
    }
    public void timeInput(String input) {
        log.info("The time is now: " + input);
    }
    
    public void setSwitch(boolean input){
        client.publish("switch", input);
    }

    public void setTemperature(int temperature){
        client.publish("temperature", temperature);
    }
    
    public void onOpen() {
        log.info("Connection opened.");
        client.publish("switch", true);  
        
    }

    public void onClose() {
        log.info("Connection closed.");
    }

    public void onError() {
        log.info("An error occured.");
    }



}
