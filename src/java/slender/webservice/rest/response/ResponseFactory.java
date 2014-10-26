/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package slender.webservice.rest.response;

import javax.ws.rs.core.Response;

/**
 *
 * @author Heinrich
 */
public class ResponseFactory {
    public static Response getResponseState(boolean isSuccess, String message, Object data) {
        return Response.ok(new ResponseState(isSuccess, message, data)).build();
    }
}
