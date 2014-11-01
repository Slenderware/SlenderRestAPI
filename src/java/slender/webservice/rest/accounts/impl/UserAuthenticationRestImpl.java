/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package slender.webservice.rest.accounts.impl;

import com.slender.domain.Users;
import com.slender.service.crud.UserCrud;
import com.slender.service.crud.impl.UserCrudImpl;
import java.util.List;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import slender.webservice.rest.accounts.UserAuthenticationRest;
import slender.webservice.rest.response.entities.SessionResponse;

/**
 *
 * @author Heinrich
 */
@Path("accounts")
public class UserAuthenticationRestImpl implements UserAuthenticationRest {

    @POST
    @Path("authenticate")
    @Override
    public Response authenticate(@FormParam("username") String username, @FormParam("password") String password) {
        UserCrud crud = new UserCrudImpl();
        
        List<Users> users = crud.findAll();
        
        for(Users u : users) {
            if(u.getUsername().equals(username)) {
                if(u.getPassword().equals(password)) {
                    return Response.ok(new SessionResponse(true, "Successfully authenticated", "sessionvariable1")).build();
                }
                else {
                    return Response.ok(new SessionResponse(false, "Incorrect password", "")).build();
                }
            }
        }

        return Response.ok(new SessionResponse(false, "User does not exist", "")).build();
    }
}
