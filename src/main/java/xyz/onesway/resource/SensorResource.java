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

import xyz.onesway.bean.Sensor;
import xyz.onesway.service.SensorService;

/**
 * @author Ben Li
 * @version Date：2015年5月21日 下午3:26:18
 */
@Path("/sensor")
public class SensorResource {

    @Autowired
    private SensorService sensorService;
    
    @Path("/add")
    @POST
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,
            MediaType.TEXT_XML })
    public boolean add(Sensor sensor) {
            return sensorService.createSensor(sensor);
    }
    
    @Path("{sensorId:[0-9]*}")
    @DELETE
    public boolean delete (@PathParam("sensorId") int sensorId) {
        return sensorService.delete(sensorId);
    }

    @Path("/update")
    @POST
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,
            MediaType.TEXT_PLAIN })
    public boolean update(Sensor sensor) {
        return sensorService.updateSensorById(sensor);
    }
    
    @Path("/list/{username}/{keyword}")
    @POST
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,
            MediaType.TEXT_XML })
    public List<Sensor> getList(@PathParam("username") String username, @PathParam("keyword") String keyword) {
        return sensorService.findUserSensor(username, keyword);
    }
    
    @Path("/list/{username}")
    @POST
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,
            MediaType.TEXT_XML })
    public List<Sensor> getList(@PathParam("username") String username) {
        return sensorService.findUserSensor(username, "");
    }
    
    @Path("{sensorId:[0-9]*}")
    @GET
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,
            MediaType.TEXT_XML })
    public Sensor findById(@PathParam("sensorId") int sensorId) {
        Sensor sensor = sensorService.findSensorById(sensorId);
        if("1".equals(sensor.getLocation())){
            sensor.setLocation("室内");
        }else {
            sensor.setLocation("室外");
        }
        return sensor;
    }
}
