/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package slender.webservice.rest.response.entities;

import com.slender.domain.Task;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.core.GenericEntity;
import javax.xml.bind.annotation.XmlRootElement;
import slender.services.core.tasks.TasksProgressService;
import slender.services.core.tasks.impl.TasksProgressServiceImpl;

/**
 *
 * @author Heinrich
 */
@XmlRootElement
public class TaskResponse {
    private int id;
    private String name;
    private String description;
    private int progress;
    private int priority;

    public TaskResponse(Task task, int progress) {
        id = task.getId();
        name = task.getTaskName();
        description = task.getTaskDesc();
        this.progress = progress;
        this.priority = task.getPriorityId();
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

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public static GenericEntity<List<TaskResponse>> getResponseEntity(List<Task> entities) {
        TasksProgressService service = new TasksProgressServiceImpl();
        
        List<TaskResponse> responses = new ArrayList<TaskResponse>();
        int progress;
        for(Task t : entities) {
            progress = service.getProgress(t.getId());
            responses.add(new TaskResponse(t, progress));
        }

        return new GenericEntity<List<TaskResponse>>(responses) {};
    }
}