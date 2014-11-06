/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package slender.webservice.rest.tasks;

import com.slender.domain.Task;
import javax.ws.rs.core.Response;

/**
 *
 * @author Heinrich
 */
public interface TasksRest {
    public Response getTask(Integer id);
    public Response getTaskAttachments(Integer taskId);
    public Response getTaskComments(Integer taskId);
    public Response getTaskUsers(Integer taskId);
    public Response getProgress(Integer taskId);
    public Response addProgress(Integer taskId, Integer userId, int hours);
    public Response addTask(Task task);
}
