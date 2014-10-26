/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package slender.webservice.rest.users;

import javax.ws.rs.core.Response;

/**
 *
 * @author Heinrich
 */
public interface UsersRest {
    public Response getUsersOfTask(Integer taskId);
}
