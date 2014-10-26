/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package slender.webservice.rest.projects.impl;

import com.google.common.collect.Lists;
import com.slender.domain.Project;
import com.slender.domain.Users;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;
import javax.xml.bind.annotation.XmlRootElement;
import slender.webservice.rest.projects.ProjectsRest;
import slender.webservice.rest.users.impl.UsersRestImpl;
import slender.webservice.services.accounts.session.UserSessions;
import slender.webservice.services.projects.ProjectsService;
import slender.webservice.services.projects.impl.ProjectsServiceImpl;

/**
 *
 * @author Heinrich
 */
@Path("/projects")
public class ProjectsRestImpl implements ProjectsRest {
    
    @POST
    @Path("getProjects")
    @Override
    public Response getProjects(@FormParam("sessionId") String sessionId) {
        Users user = UserSessions.getUser(sessionId);
        ProjectsService service = new ProjectsServiceImpl();
        
        List<Project> projects = service.getProjects(user.getId());
        
        return Response.ok(getResponseEntity(projects)).build();
    }
    
    private GenericEntity<List<ProjectResponse>> getResponseEntity(List<Project> projects) {
        List<ProjectResponse> responses = new ArrayList<ProjectResponse>();
        
        for(Project p : projects) {
            responses.add(new ProjectResponse(p));
        }
        
        return new GenericEntity<List<ProjectResponse>>(Lists.newArrayList(responses)) {};
    }
    
    @XmlRootElement
    private class ProjectResponse {
        int id;
        String name;

        public ProjectResponse(Project project) {
            this.id = project.getId();
            this.name = project.getProjectName();
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
