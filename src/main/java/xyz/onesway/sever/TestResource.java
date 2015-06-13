package xyz.onesway.sever;

import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import xyz.onesway.tool.ServiceFactory;
import at.ac.sbg.icts.spacebrew.client.SpacebrewClient;

/**
 * @author Ben Li
 * @version Date：2015年6月5日 下午2:29:01
 */
@Path("test")
public class TestResource {
    SpacebrewClient client = ServiceFactory.getServer().getClient();
    @Path("settem/{tem}")
    @POST
    public void setTemperature(@PathParam("tem") int tem) {
        
        for(int i=0;i<200;i++){
             client.publish("temperature", tem);
        }
    }
    
    @Path("close")
    @PUT
    public void close() {
        for(int i=0;i<200;i++){
            client.publish("temperature", 100);
        }
    }
    
    @Path("switch/{swtich}")
    @POST
    public void setSwtich(@PathParam("swtich") int swtich) {
        System.out.println(swtich);
        for(int i=0;i<200;i++){
                client.publish("switch", swtich);
        }
    }
    
}
