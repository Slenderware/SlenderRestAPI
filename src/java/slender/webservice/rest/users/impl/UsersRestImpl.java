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
import slender.services.core.accounts.session.UserSessions;
import slender.services.core.users.UserListsService;
import slender.services.core.users.UsersProgressService;
import slender.services.core.users.UsersService;
import slender.services.core.users.impl.UserListsServiceImpl;
import slender.services.core.users.impl.UsersProgressServiceImpl;
import slender.services.core.users.impl.UsersServiceImpl;
import slender.webservice.rest.response.entities.ProjectResponse;
import slender.webservice.rest.response.entities.SessionResponse;
import slender.webservice.rest.response.entities.SuccessResponse;
import slender.webservice.rest.response.entities.TaskResponse;
import slender.webservice.rest.response.entities.UserResponse;
import slender.webservice.rest.users.UsersRest;

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
        UserListsService service = new UserListsServiceImpl();
        List<Project> projects = service.getUserProjects(sessionId);
        
        return Response.ok(ProjectResponse.getResponseEntity(projects)).build();
    }

    @POST
    @Path("getUserTasks")
    @Override
    public Response getUserTasks(@FormParam("sessionId") String sessionId) {
        UserListsService service = new UserListsServiceImpl();
        List<Task> tasks = service.getUserTasks(sessionId);
        
        return Response.ok(TaskResponse.getResponseEntity(tasks)).build();
    }

    @POST
    @Path("getUserProjectTasks")
    @Override
    public Response getUserProjectTasks(@FormParam("sessionId") String sessionId, @FormParam("projectId") Integer projectId) {
        UserListsService service = new UserListsServiceImpl();
        List<Task> tasks = service.getUserProjectTasks(sessionId, projectId);
        
        return Response.ok(TaskResponse.getResponseEntity(tasks)).build();
    }
    
    @POST
    @Path("getTimeSpentForTask")
    @Override
    public Response getTimeSpentForTask(@FormParam("sessionId") String sessionId, @FormParam("taskId") Integer taskId) {
        UsersProgressService service = new UsersProgressServiceImpl();
        int time = service.getTimeSpentForTask(sessionId, taskId);
        
        return Response.ok(time).build();
    }

    @POST
    @Path("getTimeSpentForProject")
    @Override
    public Response getTimeSpentForProject(@FormParam("sessionId") String sessionId, @FormParam("projectId") Integer projectId) {
        UsersProgressService service = new UsersProgressServiceImpl();
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
        
        String sessionId = UserSessions.getNewSessions(newUser.getUsername());
        
        return Response.ok(new SessionResponse(true, "Success", sessionId)).build();
    }
    
    @POST
    @Path("assignUserToProject")
    @Override
     public Response addUserToProject(@FormParam("userId") Integer userId, @FormParam("projectId") Integer projId) {
         UsersService service =  new UsersServiceImpl();
         boolean rtrn = service.addUserToProject(userId, projId);
         
         if(rtrn)
            return Response.ok(new SuccessResponse(true, "Successfull")).build();
         
         return Response.ok(new SuccessResponse(false, "User does not exist")).build();
     }

    @POST
    @Path("assignUserToTask")
    @Override
    public Response addUserToTask(@FormParam("userId") Integer userId, @FormParam("taskId") Integer taskId) {
        UsersService service =  new UsersServiceImpl();
         boolean rtrn = service.addUserToTask(userId, taskId);
         
         if(rtrn)
            return Response.ok(new SuccessResponse(true, "Successfull")).build();
         
         return Response.ok(new SuccessResponse(false, "User does not exist")).build();
    }

    @POST
    @Path("getPercentageSpentForTask")
    @Override
    public Response getPercentageSpentForTask(@FormParam("sessionId") String sessionId, @FormParam("taskId") Integer taskId) {
        UsersProgressService service = new UsersProgressServiceImpl();
        double time = service.getPercentageSpentForTask(sessionId, taskId);
        
        return Response.ok(time).build();    }

    @POST
    @Path("getPercentageSpentForProject")
    @Override
    public Response getPercentageSpentForProject(@FormParam("sessionId") String sessionId, @FormParam("projectId") Integer projectId) {
        UsersProgressService service = new UsersProgressServiceImpl();
        double time = service.getPercentageSpentForProject(sessionId, projectId);
        
        return Response.ok(time).build();
    }
}
