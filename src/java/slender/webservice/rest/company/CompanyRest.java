/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package slender.webservice.rest.company;

import javax.ws.rs.core.Response;

/**
 *
 * @author Heinrich
 */
public interface CompanyRest {
    public Response addCompany(String name);
    public Response getCompanyUsers(Integer companyId);
}
