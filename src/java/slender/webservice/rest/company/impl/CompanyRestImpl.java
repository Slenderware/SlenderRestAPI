/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package slender.webservice.rest.company.impl;

import com.slender.domain.Company;
import java.util.Date;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import slender.services.core.company.CompanyService;
import slender.services.core.company.impl.CompanyServiceImpl;
import slender.webservice.rest.company.CompanyRest;
import slender.webservice.rest.response.entities.CompanyResponse;

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
    
}
