/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package slender.webservice.rest.company.impl;

import com.slender.domain.Company;
import com.slender.domain.Users;
import java.util.Date;
import java.util.List;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import slender.services.core.company.CompanyService;
import slender.services.core.company.impl.CompanyServiceImpl;
import slender.webservice.rest.company.CompanyRest;
import slender.webservice.rest.response.entities.CompanyResponse;
import slender.webservice.rest.response.entities.UserResponse;

/**
 *
 * @author Heinrich
 */
@Path("company")
public class CompanyRestImpl implements CompanyRest {
    
    @POST
    @Path("addCompany")
    @Override
    public Response addCompany(@FormParam("name") String name) {

        Company company = new Company();
        company.setCompanyName(name);
        company.setCreateDate(new Date());
        
        CompanyService service = new CompanyServiceImpl();
        Company newComp = service.addCompany(company);
        
        return Response.ok(new CompanyResponse(newComp)).build();
    }

    @POST
    @Path("getCompanyUsers")
    @Override
    public Response getCompanyUsers(@FormParam("id") Integer companyId) {
        CompanyService service = new CompanyServiceImpl();
        List<Users> users = service.getCompanyUsers(companyId);
        
        return Response.ok(UserResponse.getResponseEntity(users)).build();
    }
    
}
