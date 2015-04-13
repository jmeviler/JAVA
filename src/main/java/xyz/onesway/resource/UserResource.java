package xyz.onesway.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;

import xyz.onesway.bean.Home;
import xyz.onesway.bean.User;
import xyz.onesway.service.UserService;

/**
 * @author Ben Li
 * @version Date：2015年3月22日 下午2:17:38
 */

@Path("/user")
public class UserResource {

    @Autowired
    private UserService userService;
    
    @Path("/register")
    @POST
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,
            MediaType.TEXT_XML })
    public boolean register (User user) {
        if(user == null || user.getUsername() ==null || user.getPassword() == null){
            return false;
        } else {
            return userService.register(user);
        }
    }

    @Path("/login/{userName}/{password}")
    @POST
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,
            MediaType.TEXT_PLAIN })
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,
        MediaType.TEXT_XML })
    public User login(@PathParam("userName") String userName, @PathParam("password") String password) {
        if (userService.login(userName, password) != null) {
             return userService.login(userName, password);
         } else {
             return null;
         }
    }
    
    @Path("/updatepwd/{userId}/{password}")
    @POST
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,
            MediaType.TEXT_PLAIN })
    public boolean updatePwd (@PathParam("userId") int userId, @PathParam("password") String password) {
        return userService.updateUserPwd(userId, password);
    }
    
    @Path("/updateuser")
    @POST
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,
            MediaType.TEXT_PLAIN })
    public User updateUser (User user) {
            return userService.updateUserInfo(user);
    }

    @Path("{homeId:[0-9]*}")
    @DELETE
    public boolean delete (@PathParam("userId") int userId) {
            return userService.delete(userId);
    }

}
