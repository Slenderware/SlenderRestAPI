/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package slender.webservice.rest.accounts.impl;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import slender.services.core.accounts.UserAuthenticationService;
import slender.services.core.accounts.impl.UserAuthenticationServiceImpl;
import slender.services.core.accounts.session.UserSessions;
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
        UserAuthenticationService service = new UserAuthenticationServiceImpl();
        int response = service.authenticate(username, password);
        
        if(response == 0) {
            String sessionStr = UserSessions.getNewSessions(username);
            return Response.ok(new SessionResponse(true, "Successfully authenticated", sessionStr)).build();
        }
        else if(response == 2)
            return Response.ok(new SessionResponse(false, "User does not exist", "")).build();

        return Response.ok(new SessionResponse(false, "Incorrect password", "")).build();
    }
}
