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
 * @author Heinrich van den Ende
 * 
 *  The MIT License (MIT)

    Copyright © 2014 Slenderware

    Permission is hereby granted, free of charge, to any person obtaining a copy
    of this software and associated documentation files (the "Software"), to deal
    in the Software without restriction, including without limitation the rights
    to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
    copies of the Software, and to permit persons to whom the Software is
    furnished to do so, subject to the following conditions:

    The above copyright notice and this permission notice shall be included in all
    copies or substantial portions of the Software.

    THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
    IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
    FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
    AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
    LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
    OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
    SOFTWARE.
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
