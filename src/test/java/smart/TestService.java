package smart;

import at.ac.sbg.icts.spacebrew.client.*;

/**
 * @author Ben Li
 * @version Date：2015年6月2日 下午4:05:15
 */
public class TestService {

    public SpacebrewClient getClient() {
        String serverUri = "ws://spacebrew.icts.sbg.ac.at:9000";
       SpacebrewClient client = new SpacebrewClient(null, serverUri, "TestService", "001");
       return client;
    }
    
}
