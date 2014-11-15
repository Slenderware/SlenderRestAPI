/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package slender.webservice.rest.tasks.impl;

import com.slender.app.factory.CommentFactory;
import com.slender.app.factory.TaskFactory;
import com.slender.domain.Comment;
import com.slender.domain.Task;
import com.slender.domain.Users;
import java.util.List;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import slender.services.core.comments.CommentsService;
import slender.services.core.comments.impl.CommentsServiceImpl;
import slender.services.core.tasks.TasksService;
import slender.services.core.tasks.impl.TasksServiceImpl;
import slender.webservice.rest.request.entities.DateParam;
import slender.webservice.rest.response.entities.CommentResponse;
import slender.webservice.rest.response.entities.SuccessResponse;
import slender.webservice.rest.response.entities.TaskResponse;
import slender.webservice.rest.response.entities.UserProgressResponse;
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
        
        return Response.ok(UserProgressResponse.getResponseEntity(users, taskId)).build();
    }

    @POST
    @Path("getProgress")
    @Override
    public Response getProgress(@FormParam("id") Integer taskId) {
        TasksService service = new TasksServiceImpl();
        int progress = service.getProgress(taskId);
        
        return Response.ok(progress).build();
    }

    @POST
    @Path("addProgress")
    @Override
    public Response addProgress(@FormParam("id") Integer taskId, @FormParam("userId") Integer userId, @FormParam("hours") int hours) {
        TasksService service = new TasksServiceImpl();
        service.addProgress(taskId, userId, hours);
        
        return Response.ok(new SuccessResponse(true, "Successfully allocated time for task")).build();
    }

    @POST
    @Path("addTask")
    @Override
    public Response addTask(
            @FormParam("projectId") int projectId, 
            @FormParam("taskName") String taskName, 
            @FormParam("taskDesc") String taskDesc, 
            @FormParam("plannedStartDate") String plannedStartDate,
            @FormParam("plannedEndDate") String plannedEndDate,
            @FormParam("timeAllocation") int timeAllocation,
            @FormParam("priorityId") int priorityId) {
        
        TaskFactory factory = new TaskFactory();
        Task task = factory.getTask(projectId, taskName, taskDesc, DateParam.valueOf(plannedStartDate).getDate(), DateParam.valueOf(plannedEndDate).getDate(), null, null, timeAllocation, priorityId);
        
        try {
            TasksService service = new TasksServiceImpl();
            service.addTask(task);
            
            return Response.ok(new SuccessResponse(true, "Success")).build();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        
        return Response.ok(new SuccessResponse(false, "Failed")).build();
    }

    @POST
    @Path("getProgressPercentage")
    @Override
    public Response getProgressPercentage(@FormParam("id") Integer taskId) {
        TasksService service = new TasksServiceImpl();
        double progress = service.getProgressPercentage(taskId);
        
        return Response.ok(progress).build();    
    }

    @POST
    @Path("addTaskComment")
    @Override
    public Response addTaskComment(
            @FormParam("userId") Integer userId,
            @FormParam("taskId") Integer taskId,
            @FormParam("comment") String comment) {
        
        try {
            CommentFactory factory = new CommentFactory();
            CommentsService service = new CommentsServiceImpl();

            Comment com = factory.getComment(comment, 0, taskId, userId);
            com.setProjectId(null);
        
            service.addComment(com);
            
            return Response.ok(new SuccessResponse(true, "Success")).build();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        
        return Response.ok(new SuccessResponse(false, "Failed")).build();
    }

    @POST
    @Path("markAsComplete")
    @Override
    public Response markAsComplete(@FormParam("id") Integer taskId) {
        TasksService service = new TasksServiceImpl();
        boolean rtrn = service.markAsComplete(taskId);
        
        if(rtrn) {
             return Response.ok(new SuccessResponse(true, "Success")).build();
        }
        
        return Response.ok(new SuccessResponse(false, "Failed")).build();
    }
}
