/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package slender.webservice.rest.projects;

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
    public Response addProjectComment(
            Integer userId,
            Integer projId,
            String comment);
    public Response addProject(
            Integer creator, 
            Integer manager, 
            String name, 
            String desc,
            String startDate, 
            String endDate);
}
