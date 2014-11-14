/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package slender.webservice.rest.projects.impl;

import com.slender.app.factory.ProjectFactory;
import com.slender.domain.Comment;
import com.slender.domain.Project;
import com.slender.domain.Task;
import com.slender.domain.Users;
import java.util.List;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import slender.services.core.projects.ProjectsService;
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

    @Override
    public Response addProjectComment(Integer userId, Integer projId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
