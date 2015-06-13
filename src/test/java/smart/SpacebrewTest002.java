package smart;

import at.ac.sbg.icts.spacebrew.client.*;

/**
 * @author Ben Li
 * @version Date：2015年5月25日 下午8:17:06
 */
public class SpacebrewTest002 implements SpacebrewClientCallback{

    public void main(String[] args)
    {
        SpacebrewClient client = new SpacebrewClient(this, "ws://sandbox.spacebrew.cc:9000", "SpacebrewClient", "A simple Java client");
        client.connect();
        client.addPublisher("output2","");
        client.publish("string", "Hello world2!");
        client.addSubscriber("input2", SpacebrewMessage.TYPE_STRING, "receive");
    }
    
    public void receive(String message)
    {
        System.out.println("Received: " + message);
    }

    
    public void onOpen() {
        // TODO Auto-generated method stub
        System.out.println("Connection to server002 opened.");
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
