package xyz.onesway.sever;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.Request;

import org.apache.http.HttpRequest;
import org.apache.struts2.ServletActionContext;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opensymphony.xwork2.ActionContext;

import xyz.onesway.bean.Temperature;
import xyz.onesway.dao.TemDao;
import xyz.onesway.tool.GetDate;
import at.ac.sbg.icts.spacebrew.client.*;

/**
 * @author Ben Li
 * @version Date：2015年5月25日 下午5:56:56
 */
public class Server implements SpacebrewClientCallback{
    private final Logger log = LoggerFactory.getLogger(SpacebrewClient.class);
    private static SpacebrewClient client;
    
    public static void main(String[] args) {
        new Server();
    }
    
    public Server()
    {
//        String serverUri = "ws://spacebrew.icts.sbg.ac.at:9000";
        String serverUri = "ws://sandbox.spacebrew.cc:9000"; 
        client = new SpacebrewClient(this, serverUri, "Server(Server)");

        client.addSubscriber("switch", SpacebrewMessage.TYPE_RANGE, "switchInput");
        client.addSubscriber("temperature", SpacebrewMessage.TYPE_RANGE, "temperatureInput");
        client.addSubscriber("time", SpacebrewMessage.TYPE_STRING, "timeInput");

        // add some subscribers
        client.addPublisher("switch", 100);
        client.addPublisher("temperature", 0);
        client.addPublisher("time", "");
        client.connect();
    }

    public static SpacebrewClient getClient(){
        return client;
    }
    
    public void switchInput (int input) {
        client.publish("switch", input);
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
                client.publish("temperature", rangeValue);
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
