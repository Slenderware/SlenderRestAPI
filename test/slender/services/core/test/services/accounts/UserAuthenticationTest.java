/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package slender.services.core.test.services.accounts;

import javax.ws.rs.core.Response;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import slender.services.core.accounts.UserAuthenticationService;
import slender.services.core.accounts.impl.UserAuthenticationServiceImpl;
import slender.services.core.test.TestVal;

/**
 *
 * @author Heinrich
 */
public class UserAuthenticationTest {
    
    public UserAuthenticationTest() {
    }
    @Test
    public void testUserAuthentication() {
        UserAuthenticationService service = new UserAuthenticationServiceImpl();
        
        // Test for successful authentication
        int response = service.authenticate(TestVal.fString + "1", TestVal.fString + "1");
        Assert.assertEquals(response, 0);
        
        // Test for failed authentication (wrong password)
        response = service.authenticate(TestVal.fString + "1", "qwe");
        Assert.assertEquals(response, 2);
        
        // Test for failed authentication (invalid username)
        response = service.authenticate("asd", TestVal.fString + "1");
        Assert.assertEquals(response, 1);
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
    }

    @AfterMethod
    public void tearDownMethod() throws Exception {
    }
}
