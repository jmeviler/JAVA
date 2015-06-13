package smart;

import at.ac.sbg.icts.spacebrew.client.SpacebrewClient;
import xyz.onesway.resource.UserResource;

/**
 * @author Ben Li
 * @version Date：2015年5月26日 下午8:16:24
 */
public class Test001 {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        SpacebrewServiceServer server = new SpacebrewServiceServer();
        while(true){
            System.out.println("111");
            if(server.getClient().isConnected() == true){
               break;
            }
        }
        SpacebrewClient client = server.getClient();
        for(int i =0;i<10;i++){
            System.out.println("000");
            client.publish("switch", true);
        }
       
 
    }

}
