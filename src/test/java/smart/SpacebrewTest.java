package smart;

import at.ac.sbg.icts.spacebrew.client.*;

/**
 * @author Ben Li
 * @version Date：2015年5月25日 下午8:17:06
 */
public class SpacebrewTest implements SpacebrewClientCallback{

    public void main(String[] args)
    {
        SpacebrewClient client = new SpacebrewClient(this, "ws://sandbox.spacebrew.cc:9000", "SpacebrewClient", "A simple Java client");
        client.connect();
//        client.addPublisher("output","");
        client.addPublisher("ouput", "message", "100");
        client.publish("string", "Hello world!");
        client.addSubscriber("input", SpacebrewMessage.TYPE_STRING, "receive");
    }
    
    public void receive(String message)
    {
        System.out.println("Received: " + message);
    }

    
    public void onOpen() {
        // TODO Auto-generated method stub
        System.out.println("Connection to server opened.");
    }

    public void onClose() {
        // TODO Auto-generated method stub
        System.out.println("Connection to server closed.");
    }

    public void onError() {
        // TODO Auto-generated method stub
        System.out.println("Error occurred.");
    }

}
