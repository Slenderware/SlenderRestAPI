/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package slender.webservice.rest.projects.impl;

import com.slender.app.factory.CommentFactory;
import com.slender.app.factory.ProjectFactory;
import com.slender.domain.Comment;
import com.slender.domain.Project;
import com.slender.domain.Task;
import com.slender.domain.Users;
import java.util.List;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import slender.services.core.comments.CommentsService;
import slender.services.core.comments.impl.CommentsServiceImpl;
import slender.services.core.projects.ProjectListsService;
import slender.services.core.projects.ProjectsProgressService;
import slender.services.core.projects.ProjectsService;
import slender.services.core.projects.impl.ProjectListsServiceImpl;
import slender.services.core.projects.impl.ProjectsProgressServiceImpl;
import slender.services.core.projects.impl.ProjectsServiceImpl;
import slender.services.core.users.UsersService;
import slender.services.core.users.impl.UsersServiceImpl;
import slender.webservice.rest.projects.ProjectsRest;
import slender.webservice.rest.request.entities.DateParam;
import slender.webservice.rest.response.entities.CommentResponse;
import slender.webservice.rest.response.entities.SuccessResponse;
import slender.webservice.rest.response.entities.TaskResponse;
import slender.webservice.rest.response.entities.UserResponse;

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
@Path("/projects")
public class ProjectsRestImpl implements ProjectsRest {

    @POST
    @Path("getProject")
    @Override
    public Response getProject(@FormParam("id") Integer projId) {
        ProjectsService service = new ProjectsServiceImpl();
        
        Project project = service.getProject(projId);
        
        return Response.ok(project).build();
    }

    @POST
    @Path("getProjectUsers")
    @Override
    public Response getProjectUsers(@FormParam("id") Integer projId) {
        ProjectListsService service = new ProjectListsServiceImpl();
        List<Users> users = service.getProjectUsers(projId);
        
        return Response.ok(UserResponse.getResponseEntity(users)).build();
    }

    @POST
    @Path("getProjectTasks")
    @Override
    public Response getProjectTasks(@FormParam("id") Integer projId) {
        ProjectListsService service = new ProjectListsServiceImpl();
        List<Task> tasks = service.getProjectTasks(projId);
        
        return Response.ok(TaskResponse.getResponseEntity(tasks)).build();
    }

    @POST
    @Path("getProjectComments")
    @Override
    public Response getProjectComments(@FormParam("id") Integer projId) {
        ProjectListsService service = new ProjectListsServiceImpl();
        List<Comment> comments = service.getProjectComments(projId);
        
        return Response.ok(CommentResponse.getResponseEntity(comments)).build();
    }

    @POST
    @Path("getProjectProgress")
    @Override
    public Response getProjectProgress(@FormParam("id") Integer projId) {
        ProjectsProgressService service = new ProjectsProgressServiceImpl();
        int progress = service.getProjectProgress(projId);
        
        return Response.ok(progress).build();
    }

    @POST
    @Path("addProject")
    @Override
    public Response addProject(
            @FormParam("projectCreator")Integer creator, 
            @FormParam("projectManager")Integer manager, 
            @FormParam("projectName")String name, 
            @FormParam("projectDescription")String desc,
            @FormParam("startDate")String startDate, 
            @FormParam("endDate")String endDate) {
        
        ProjectFactory factory = new ProjectFactory();
        Project project = factory.getProject(creator, manager, name, desc, DateParam.valueOf(startDate).getDate(), DateParam.valueOf(endDate).getDate());
        
        try {
            ProjectsService service = new ProjectsServiceImpl();
            Project rtrn = service.addProject(project);

            UsersService userService = new UsersServiceImpl();
            userService.addUserToProject(creator, rtrn.getId());
            return Response.ok(new SuccessResponse(true, "Successful")).build();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        
        return Response.ok(new SuccessResponse(false, "Failed")).build();
    }

    @POST
    @Path("addProjectComment")
    @Override
    public Response addProjectComment(
            @FormParam("userId") Integer userId,
            @FormParam("projectId") Integer projId,
            @FormParam("comment") String comment) {
        
        try {
            CommentFactory factory = new CommentFactory();
            CommentsService service = new CommentsServiceImpl();

            Comment com = factory.getComment(comment, projId, 0, userId);
            com.setTaskId(null);
        
            service.addComment(com);
            
            return Response.ok(new SuccessResponse(true, "Success")).build();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        
        return Response.ok(new SuccessResponse(false, "Failed")).build();
    }
}
