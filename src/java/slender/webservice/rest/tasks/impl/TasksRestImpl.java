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
import slender.services.core.tasks.TaskListsService;
import slender.services.core.tasks.TasksProgressService;
import slender.services.core.tasks.TasksService;
import slender.services.core.tasks.impl.TaskListsServiceImpl;
import slender.services.core.tasks.impl.TasksProgressServiceImpl;
import slender.services.core.tasks.impl.TasksServiceImpl;
import slender.webservice.rest.request.entities.DateParam;
import slender.webservice.rest.response.entities.CommentResponse;
import slender.webservice.rest.response.entities.SuccessResponse;
import slender.webservice.rest.response.entities.TaskResponse;
import slender.webservice.rest.response.entities.UserProgressResponse;
import slender.webservice.rest.tasks.TasksRest;

/**
 * @author Heinrich van den Ende
 * 
 *  The MIT License (MIT)

    Copyright © 2014 Slenderware

    Permission is hereby granted, free of charge, to any person obtaining a copy
    of this software and associated documentation files (the "Software"), to deal
    in the Software without restriction, including without limitation the rights
    to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
    copies of the Software, and to permit persons to whom the Software is
    furnished to do so, subject to the following conditions:

    The above copyright notice and this permission notice shall be included in all
    copies or substantial portions of the Software.

    THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
    IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
    FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
    AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
    LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
    OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
    SOFTWARE.
 */
@Path("/tasks")
public class TasksRestImpl implements TasksRest {

    @POST
    @Path("getTask")
    @Override
    public Response getTask(@FormParam("id") Integer id) {
        TasksService service = new TasksServiceImpl();
        TasksProgressService progressService = new TasksProgressServiceImpl();
        Task task = service.getTask(id);
        int progress = progressService.getProgress(id);
        
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
        TaskListsService service = new TaskListsServiceImpl();
        List<Comment> comments = service.getTaskComments(taskId);
        
        return Response.ok(CommentResponse.getResponseEntity(comments)).build();
    }

    @POST
    @Path("getTaskUsers")
    @Override
    public Response getTaskUsers(@FormParam("id") Integer taskId) {
        TaskListsService service = new TaskListsServiceImpl();
        List<Users> users = service.getTaskUsers(taskId);
        
        return Response.ok(UserProgressResponse.getResponseEntity(users, taskId)).build();
    }

    @POST
    @Path("getProgress")
    @Override
    public Response getProgress(@FormParam("id") Integer taskId) {
        TasksProgressService service = new TasksProgressServiceImpl();
        int progress = service.getProgress(taskId);
        
        return Response.ok(progress).build();
    }

    @POST
    @Path("addProgress")
    @Override
    public Response addProgress(@FormParam("id") Integer taskId, @FormParam("userId") Integer userId, @FormParam("hours") int hours) {
        TasksProgressService service = new TasksProgressServiceImpl();
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
        TasksProgressService service = new TasksProgressServiceImpl();
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
        TasksProgressService service = new TasksProgressServiceImpl();
        boolean rtrn = service.markAsComplete(taskId);
        
        if(rtrn) {
             return Response.ok(new SuccessResponse(true, "Success")).build();
        }
        
        return Response.ok(new SuccessResponse(false, "Failed")).build();
    }
}
