package xyz.onesway.tool;

import xyz.onesway.sever.Server;

/**
 * @author Ben Li
 * @version Date：2015年6月7日 下午3:37:56
 */
public class ServiceFactory {
    private static Server server = null;
    
    static{
        server= new Server();
    }
    
    public static Server getServer(){
        return server;
    }
}
