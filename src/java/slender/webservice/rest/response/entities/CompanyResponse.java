/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package slender.webservice.rest.response.entities;

import com.slender.domain.Company;
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
public class CompanyResponse {
    int id;
    String name;

    public CompanyResponse(Company entity) {
        this.name = entity.getCompanyName();
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
    
    public static GenericEntity<List<CompanyResponse>> getResponseEntity(List<Company> entities) {
        List<CompanyResponse> responses = new ArrayList<CompanyResponse>();
        
        for(Company c : entities) {
            responses.add(new CompanyResponse(c));
        }
        
        return new GenericEntity<List<CompanyResponse>>(responses) {};
    }
}
