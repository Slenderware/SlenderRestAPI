/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package slender.webservice.rest.projects;

import com.slender.domain.Project;
import java.util.Date;
import javax.ws.rs.core.Response;
import slender.webservice.rest.request.entities.DateParam;

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
    public Response addProject(Integer creator, Integer manager, String name, String desc,
            String startDate, String endDate);
}
