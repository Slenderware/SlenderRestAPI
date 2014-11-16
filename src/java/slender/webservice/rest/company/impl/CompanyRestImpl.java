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
