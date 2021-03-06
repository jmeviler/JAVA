package xyz.onesway.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;

import xyz.onesway.bean.Home;
import xyz.onesway.service.HomeService;

@Path("/home")
public class HomeResource {
    @Autowired
    private HomeService homeService;

    /**
     * add home
     * 
     * @param homeId
     * @return Home
     */
    @Path("{homeId:[0-9]*}")
    @GET
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,
            MediaType.TEXT_PLAIN })
    public Home getHome(@PathParam("homeId") int homeId) {
        Home home = homeService.findById(homeId);
        return home;
    }

    /**
     * save
     * 
     * @param home
     * @return true or false
     */
    @Path("/add")
    @POST
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,
            MediaType.TEXT_XML })
    public boolean saveHome(Home home) {
        if(homeService.findHomeByName(home.getName())){
            return false;
        }else {
            return homeService.save(home);
        }
    }

    @Path("/login/{name}/{pwd}")
    @POST
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,
            MediaType.TEXT_XML })
    public Home login(@PathParam("name") String name, @PathParam("pwd") String pwd){
        Home home =homeService.findHomeByName(name, pwd); 
        if(home != null && home.getDeleted() != 1){
            return home;
        }else {
            return null;
        }
    }
    
    /**
     * delete
     * 
     * @param homeId
     * @return true or false
     */
    @Path("{homeId:[0-9]*}")
    @DELETE
    public String deleteHome(@PathParam("homeId") int homeId) {
        if (homeService.deleteById(homeId)) {
            return "Deleted homeid" + homeId + "success!";
        } else {
            return "Deleted homeid" + homeId + "fail!";
        }
    }

    /**
     * update 
     * @param homeId
     * @param home
     * @return update success or not
     */
    @Path("{homeId:[0-9]*}")
    @PUT
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,
            MediaType.TEXT_XML })
    public String updateHome(@PathParam("homeId") int homeId,Home home) {
        if(home == null){
            return "some thing is empty!";
        }else{
            return "update homeId"+homeId+homeService.update(homeId,home);
        }
    }


}
