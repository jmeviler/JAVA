package xyz.onesway.resource;

import java.util.List;

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

import at.ac.sbg.icts.spacebrew.test.*;
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
        if(userService.findUserByName(user.getUsername())) {
            return false;
        }else {
            if(user == null || user.getUsername() ==null || user.getPassword() == null){
                return false;
            } else {
                return userService.register(user);
            }
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
             User user = userService.login(userName, password);
             if(user.getDeleted() == 1){
                 return null;
             }
             else {
                 return user;
             }
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

    @Path("{userId:[0-9]*}")
    @DELETE
    public boolean delete (@PathParam("userId") int userId) {
            return userService.delete(userId);
    }
    
    @Path("{homeId:[0-9]*}")
    @GET
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,
            MediaType.TEXT_XML })
    public List<User> homeUser(@PathParam("homeId") int homeId) {
        return userService.findUseByHomeId(homeId);
    }
}
