/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package slender.webservice.rest.tasks.impl;

import com.google.common.collect.Lists;
import com.slender.domain.Task;
import com.slender.domain.Users;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;
import javax.xml.bind.annotation.XmlRootElement;
import slender.webservice.rest.projects.impl.ProjectsRestImpl;
import slender.webservice.rest.tasks.TasksRest;
import slender.webservice.rest.users.impl.UsersRestImpl;
import slender.webservice.services.tasks.TasksService;
import slender.webservice.services.tasks.impl.TasksServiceImpl;

/**
 *
 * @author Heinrich
 */
@Path("/tasks")
public class TasksRestImpl implements TasksRest {
    
    @POST
    @Path("getTasksFromProject")
    @Override
    public Response getTasks(@FormParam("id") Integer projectId) {
            TasksService service = new TasksServiceImpl();
            List<Task> tasks = service.getTasks(projectId);
        
            List<TaskResponse> responses = new ArrayList<TaskResponse>();
            int progress;
            for(Task t : tasks) {
                progress = service.getProgress(t.getId());
                responses.add(new TaskResponse(t, progress));
            }
            
            GenericEntity<List<TaskResponse>> entity = new GenericEntity<List<TaskResponse>>(Lists.newArrayList(responses)) {};
            return Response.ok(entity).build();
    }
    
    @XmlRootElement
    private class TaskResponse {
        private int id;
        private String name;
        private String description;
        private int progress;

        public TaskResponse(Task task, int progress) {
            id = task.getId();
            name = task.getTaskName();
            description = task.getTaskDesc();
            this.progress = progress;
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

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public int getProgress() {
            return progress;
        }

        public void setProgress(int progress) {
            this.progress = progress;
        }
    }
    
}
