package xyz.onesway.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;

import xyz.onesway.bean.Task;
import xyz.onesway.service.TaskService;

/**
 * @author Ben Li
 * @version Date：2015年5月2日 下午11:58:50
 */
@Path("/task")
public class TaskResource {

    @Autowired
    private TaskService taskService;
    
    @Path("/add")
    @POST
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,
            MediaType.TEXT_XML })
    public boolean register (Task task) {
        System.out.println(task.getGoal());
        return taskService.createTask(task);
    }

    @Path("{taskId:[0-9]*}")
    @DELETE
    public boolean delete(@PathParam("taskId") int taskId) {
        System.out.println(taskId);
        return taskService.delete(taskId);
    }

    @Path("/update/{taskId}/{goal}")
    @POST
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,
            MediaType.TEXT_PLAIN })
    public boolean updateGoal(@PathParam("taskId") int taskId, @PathParam("goal") String goal) {
        return taskService.updateGoal(taskId, goal);
    }
}
