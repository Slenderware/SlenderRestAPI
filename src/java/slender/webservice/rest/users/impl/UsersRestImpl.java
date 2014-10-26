/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package slender.webservice.rest.users.impl;

import com.google.common.collect.Lists;
import com.slender.domain.Users;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;
import javax.xml.bind.annotation.XmlRootElement;
import slender.webservice.rest.users.UsersRest;
import slender.webservice.services.users.UsersService;
import slender.webservice.services.users.impl.UsersServiceImpl;

/**
 *
 * @author Heinrich
 */
@Path("/users")
public class UsersRestImpl implements UsersRest {

    @POST
    @Path("getUsersOfTask")
    @Override
    public Response getUsersOfTask(@FormParam("id") Integer taskId) {
        UsersService service = new UsersServiceImpl();
        List<Users> users = service.getUsersFromTask(taskId);
        
        return Response.ok(getResponseEntity(users)).build();
    }
    
    private GenericEntity<List<UserResponse>> getResponseEntity(List<Users> users) {
        List<UserResponse> responses = new ArrayList<UserResponse>();
        
        for(Users u : users) {
            responses.add(new UserResponse(u));
        }
        
        return new GenericEntity<List<UserResponse>>(Lists.newArrayList(responses)) {};
    }
    
    @XmlRootElement
    private class UserResponse {
        private Integer id;
        private String username;
        
        public UserResponse(Users user) {
            this.id = user.getId();
            this.username = user.getUsername();
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
    }
}
