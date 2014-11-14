/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package slender.webservice.rest.users.impl;

import com.slender.app.factory.UsersFactory;
import com.slender.domain.Project;
import com.slender.domain.Task;
import com.slender.domain.Users;
import java.util.Date;
import java.util.List;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import slender.services.core.users.UsersService;
import slender.services.core.users.impl.UsersServiceImpl;
import slender.webservice.rest.response.entities.ProjectResponse;
import slender.webservice.rest.response.entities.SuccessResponse;
import slender.webservice.rest.response.entities.TaskResponse;
import slender.webservice.rest.response.entities.UserResponse;
import slender.webservice.rest.users.UsersRest;

/**
 *
 * @author Heinrich
 */
@Path("/users")
public class UsersRestImpl implements UsersRest {

    @POST
    @Path("getUserBySession")
    @Override
    public Response getUserBySession(@FormParam("sessionId") String sessionId) {
        UsersService service = new UsersServiceImpl();
        Users user = service.getUserBySession(sessionId);
        
        return Response.ok(new UserResponse(user)).build();
    }

    @POST
    @Path("getUserProjects")
    @Override
    public Response getUserProjects(@FormParam("sessionId") String sessionId) {
        UsersService service = new UsersServiceImpl();
        List<Project> projects = service.getUserProjects(sessionId);
        
        return Response.ok(ProjectResponse.getResponseEntity(projects)).build();
    }

    @POST
    @Path("getUserTasks")
    @Override
    public Response getUserTasks(@FormParam("sessionId") String sessionId) {
        UsersService service = new UsersServiceImpl();
        List<Task> tasks = service.getUserTasks(sessionId);
        
        return Response.ok(TaskResponse.getResponseEntity(tasks)).build();
    }

    @POST
    @Path("getUserProjectTasks")
    @Override
    public Response getUserProjectTasks(@FormParam("sessionId") String sessionId, @FormParam("projectId") Integer projectId) {
        UsersService service = new UsersServiceImpl();
        List<Task> tasks = service.getUserProjectTasks(sessionId, projectId);
        
        return Response.ok(TaskResponse.getResponseEntity(tasks)).build();
    }
    
    @POST
    @Path("getTimeSpentForTask")
    @Override
    public Response getTimeSpentForTask(@FormParam("sessionId") String sessionId, @FormParam("taskId") Integer taskId) {
        UsersService service = new UsersServiceImpl();
        int time = service.getTimeSpentForTask(sessionId, taskId);
        
        return Response.ok(time).build();
    }

    @POST
    @Path("getTimeSpentForProject")
    @Override
    public Response getTimeSpentForProject(@FormParam("sessionId") String sessionId, @FormParam("projectId") Integer projectId) {
        UsersService service = new UsersServiceImpl();
        int time = service.getTimeSpentForProject(sessionId, projectId);
        
        return Response.ok(time).build();
    }

    @POST
    @Path("addUser")
    @Override
    public Response addUser(
            @FormParam("firstName") String firstName, 
            @FormParam("lastName") String lastName, 
            @FormParam("username") String username, 
            @FormParam("email") String email,
            @FormParam("password") String password, 
            @FormParam("roleId") Integer roleId, 
            @FormParam("companyId") Integer companyId) {
        
        UsersFactory fact = new UsersFactory();
        Users user = fact.getUsers(firstName, lastName, username, email, password, roleId, companyId);
        user.setLastSeen(new Date());
        
        UsersService service =  new UsersServiceImpl();
        Users newUser = service.addUser(user);
        
        return Response.ok(new UserResponse(newUser)).build();
    }

    @POST
    @Path("addAdminUser")
    @Override
    public Response addAdminUser(
            @FormParam("firstName") String firstName, 
            @FormParam("lastName") String lastName, 
            @FormParam("username") String username, 
            @FormParam("email") String email,
            @FormParam("password") String password, 
            @FormParam("roleId") Integer roleId, 
            @FormParam("companyId") Integer companyId) {
        
        UsersFactory fact = new UsersFactory();
        Users user = fact.getUsers(firstName, lastName, username, email, password, roleId, companyId);
        user.setLastSeen(new Date());
        
        UsersService service =  new UsersServiceImpl();
        Users newUser = service.addAdminUser(user);
        
        return Response.ok(new UserResponse(newUser)).build();
    }
    
    @POST
    @Path("addUserToProject")
    @Override
     public Response addUserToProject(Integer userId, Integer projId) {
         UsersService service =  new UsersServiceImpl();
         boolean rtrn = service.addUserToProject(userId, projId);
         
         if(rtrn)
            return Response.ok(new SuccessResponse(true, "Successfull")).build();
         
         return Response.ok(new SuccessResponse(false, "User does not exist")).build();
     }

    @POST
    @Path("addUserToTask")
    @Override
    public Response addUserToTask(Integer userId, Integer taskId) {
        UsersService service =  new UsersServiceImpl();
         boolean rtrn = service.addUserToTask(userId, taskId);
         
         if(rtrn)
            return Response.ok(new SuccessResponse(true, "Successfull")).build();
         
         return Response.ok(new SuccessResponse(false, "User does not exist")).build();
    }
}
