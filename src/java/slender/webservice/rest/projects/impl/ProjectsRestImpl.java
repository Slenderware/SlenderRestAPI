/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package slender.webservice.rest.projects.impl;

import com.slender.domain.Comment;
import com.slender.domain.Project;
import com.slender.domain.Task;
import com.slender.domain.Users;
import java.util.List;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import slender.webservice.rest.projects.ProjectsRest;
import slender.webservice.rest.response.entities.CommentResponse;
import slender.webservice.rest.response.entities.TaskResponse;
import slender.webservice.rest.response.entities.UserResponse;
import slender.webservice.services.projects.ProjectsService;
import slender.webservice.services.projects.impl.ProjectsServiceImpl;

/**
 *
 * @author Heinrich
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
        ProjectsService service = new ProjectsServiceImpl();
        List<Users> users = service.getProjectUsers(projId);
        
        return Response.ok(UserResponse.getResponseEntity(users)).build();
    }

    @POST
    @Path("getProjectTasks")
    @Override
    public Response getProjectTasks(@FormParam("id") Integer projId) {
        ProjectsService service = new ProjectsServiceImpl();
        List<Task> tasks = service.getProjectTasks(projId);
        
        return Response.ok(TaskResponse.getResponseEntity(tasks)).build();
    }

    @POST
    @Path("getProjectComments")
    @Override
    public Response getProjectComments(@FormParam("id") Integer projId) {
        ProjectsService service = new ProjectsServiceImpl();
        List<Comment> comments = service.getProjectComments(projId);
        
        return Response.ok(CommentResponse.getResponseEntity(comments)).build();
    }

    @POST
    @Path("getProjectProgress")
    @Override
    public Response getProjectProgress(@FormParam("id") Integer projId) {
        ProjectsService service = new ProjectsServiceImpl();
        int progress = service.getProjectProgress(projId);
        
        return Response.ok(progress).build();
    }
}
