/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package slender.webservice.rest.projects;

import com.slender.domain.Project;
import javax.ws.rs.core.Response;

/**
 *
 * @author Heinrich
 */
public interface ProjectsRest {
    public Response getProject(Integer id);
    public Response getProjectUsers(Integer projId);
    public Response getProjectTasks(Integer projId);
    public Response getProjectComments(Integer projId);
    public Response getProjectProgress(Integer projId);
    public Response addProject(Project project);
}
