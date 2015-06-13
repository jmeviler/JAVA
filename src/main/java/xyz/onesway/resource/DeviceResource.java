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

import xyz.onesway.bean.Device;
import xyz.onesway.service.DeviceService;

/**
 * @author Ben Li
 * @version Date：2015年5月2日 下午9:56:11
 */
@Path("/device")
public class DeviceResource {
    @Autowired
    private DeviceService deviceService;
    
    @Path("/add")
    @POST
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,
            MediaType.TEXT_XML })
    public boolean register (Device device) {
        return deviceService.createDevice(device);
    }
    
    @Path("{deviceId:[0-9]*}")
    @DELETE
    public boolean delete(@PathParam("deviceId") int deviceId) {
        return deviceService.delete(deviceId);
    }
    
    @Path("/update")
    @POST
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,
            MediaType.TEXT_XML })
    public boolean update(Device device){
        return deviceService.updateDeviceById(device);
    }
    
    @Path("/list/{username}/{keyword}")
    @POST
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,
            MediaType.TEXT_XML })
    public List<Device> getList(@PathParam("username") String username, @PathParam("keyword") String keyword) {
        return deviceService.findUserDevice(username, keyword);
    }
    
    @Path("/list/{username}")
    @POST
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,
            MediaType.TEXT_XML })
    public List<Device> getList(@PathParam("username") String username) {
        return deviceService.findUserDevice(username, "");
    }
    
    @Path("{deviceId:[0-9]*}")
    @GET
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,
            MediaType.TEXT_XML })
    public Device findById (@PathParam("deviceId") int deviceId) {
            Device device = deviceService.findDeviceById(deviceId); 
            if("1".equals(device.getLocation())){
                device.setLocation("室内");
            } else {
                device.setLocation("室外");
            }
            return device;
    }
}
