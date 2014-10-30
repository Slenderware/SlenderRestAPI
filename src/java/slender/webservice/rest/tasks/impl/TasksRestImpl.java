/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package slender.webservice.rest.tasks.impl;

import com.slender.domain.Comment;
import com.slender.domain.Task;
import com.slender.domain.Users;
import java.util.List;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import slender.services.core.tasks.TasksService;
import slender.services.core.tasks.impl.TasksServiceImpl;
import slender.webservice.rest.response.entities.CommentResponse;
import slender.webservice.rest.response.entities.TaskResponse;
import slender.webservice.rest.response.entities.UserResponse;
import slender.webservice.rest.tasks.TasksRest;

/**
 *
 * @author Heinrich
 */
@Path("/tasks")
public class TasksRestImpl implements TasksRest {

    @POST
    @Path("getTask")
    @Override
    public Response getTask(@FormParam("id") Integer id) {
        TasksService service = new TasksServiceImpl();
        Task task = service.getTask(id);
        int progress = service.getProgress(id);
        
        return Response.ok(new TaskResponse(task, progress)).build();
    }

    @Override
    public Response getTaskAttachments(@FormParam("id") Integer taskId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @POST
    @Path("getTaskComments")
    @Override
    public Response getTaskComments(@FormParam("id") Integer taskId) {
        TasksService service = new TasksServiceImpl();
        List<Comment> comments = service.getTaskComments(taskId);
        
        return Response.ok(CommentResponse.getResponseEntity(comments)).build();
    }

    @POST
    @Path("getTaskUsers")
    @Override
    public Response getTaskUsers(@FormParam("id") Integer taskId) {
        TasksService service = new TasksServiceImpl();
        List<Users> users = service.getTaskUsers(taskId);
        
        return Response.ok(UserResponse.getResponseEntity(users)).build();
    }

    @POST
    @Path("getProgress")
    @Override
    public Response getProgress(@FormParam("id") Integer taskId) {
        TasksService service = new TasksServiceImpl();
        int progress = service.getProgress(taskId);
        
        return Response.ok(progress).build();
    }

    @Override
    public Response addProgress(@FormParam("id") Integer taskId, @FormParam("userId") Integer userId, @FormParam("hours") int hours) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
