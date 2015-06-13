package xyz.onesway.resource;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;

import xyz.onesway.bean.Actuator;
import xyz.onesway.service.ActuatorService;

/**
 * @author Ben Li
 * @version Date：2015年5月21日 下午3:25:43
 */
@Path("/actuator")
public class ActuatorResource {
    
    @Autowired
    private ActuatorService actuatorService;
    
    @Path("/add")
    @POST
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,
            MediaType.TEXT_XML })
    public boolean add(Actuator actuator) {
            return actuatorService.createActuator(actuator);
    }
    
    @Path("{actuatorId:[0-9]*}")
    @DELETE
    public boolean delete (@PathParam("actuatorId") int actuatorId) {
            return actuatorService.delete(actuatorId);
    }
    
    @Path("/update")
    @POST
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,
            MediaType.TEXT_PLAIN })
    public boolean update(Actuator actuator) {
         return actuatorService.updateActuatorById(actuator);
    }
    
    @Path("/list/{username}/{keyword}")
    @POST
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,
            MediaType.TEXT_XML })
    public List<Actuator> getList(@PathParam("username") String username, @PathParam("keyword") String keyword) {
        return actuatorService.findUserActuator(username, keyword);
    }
    
    @Path("/list/{username}")
    @POST
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,
            MediaType.TEXT_XML })
    public List<Actuator> getList(@PathParam("username") String username) {
        return actuatorService.findUserActuator(username,"");
    }
    
    @Path("{actuatorId:[0-9]*}")
    @GET
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,
            MediaType.TEXT_XML })
    public Actuator findById (@PathParam("actuatorId") int actuatorId) {
            Actuator actuator = actuatorService.findActuatorById(actuatorId);
            if("1".equals(actuator.getLocation())){
                actuator.setLocation("室内");
            }else {
                actuator.setLocation("室外");
            }
            return actuator;
    }
}
