package xyz.onesway.resource;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import xyz.onesway.service.SpacebrewService;

/**
 * @author Ben Li
 * @version Date：2015年5月27日 上午11:28:04
 */
@Path("/space")
public class SpacebrewResource {
    
    private SpacebrewService spacebrewService =  new SpacebrewService();
    
    @Path("/swtich/{swtich}")
    @POST
    public void setSwtich(@PathParam("swtich") boolean swtich) {
        spacebrewService.setSwitch(swtich);
    }
    
    @Path("/temperature/{temperature}")
    @POST
    public void setTemperature(@PathParam("temperature") int temperature) {
        spacebrewService.temperatureInput(temperature);
    }
    
    
}
