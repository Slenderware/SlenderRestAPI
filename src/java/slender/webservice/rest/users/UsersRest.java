/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package slender.webservice.rest.users;

import com.slender.domain.Users;
import javax.ws.rs.core.Response;

/**
 *
 * @author Heinrich
 */
public interface UsersRest {
    public Response getUserBySession(String sessionId);
    public Response getUserProjects(String sessionId);
    public Response getUserTasks(String sessionId);
    public Response getTimeSpentForTask(String sessionId, Integer taskId);
    public Response getTimeSpentForProject(String sessionId, Integer projectId);
    public Response addUser(String firstName, String lastName, String username, String email,
            String password, Integer roleId, Integer companyId);
}
