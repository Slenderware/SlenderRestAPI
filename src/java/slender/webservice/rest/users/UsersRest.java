/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package slender.webservice.rest.users;

import com.slender.domain.Project;
import com.slender.domain.Task;
import com.slender.domain.Users;
import java.util.List;
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
}
