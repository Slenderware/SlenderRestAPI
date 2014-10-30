/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package slender.webservice.rest.response.entities;

import com.slender.domain.Project;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.core.GenericEntity;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Heinrich
 */
@XmlRootElement
public class ProjectResponse {
    int id;
    String name;

    public ProjectResponse(Project entity) {
        this.name = entity.getProjectName();
        this.id = entity.getId();
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
    
    public static GenericEntity<List<ProjectResponse>> getResponseEntity(List<Project> entities) {
        List<ProjectResponse> responses = new ArrayList<ProjectResponse>();
        
        for(Project p : entities) {
            responses.add(new ProjectResponse(p));
        }
        
        return new GenericEntity<List<ProjectResponse>>(responses) {};
    }
}
