/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package slender.webservice.rest.response.entities;

import com.slender.domain.Users;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.core.GenericEntity;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Heinrich
 */
@XmlRootElement
public class UserResponse {
    private Integer id;
    private Integer roleId;
    private Integer companyId;
    private String username;

    public UserResponse(Users user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.roleId = user.getRoleId();
        this.companyId = user.getCompanyId();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public static GenericEntity<List<UserResponse>> getResponseEntity(List<Users> entities) {
        List<UserResponse> responses = new ArrayList<UserResponse>();

        for(Users e : entities) {
            responses.add(new UserResponse(e));
        }

        return new GenericEntity<List<UserResponse>>(responses) {};
    }
}
