/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package slender.services.core.test.services.accounts.sessions;

import com.slender.domain.Users;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import slender.services.core.accounts.session.UserSessions;
import slender.services.core.test.TestVal;

/**
 *
 * @author Heinrich
 */
public class UserSessionsTest {
    
    public UserSessionsTest() {
    }

    @Test
    public void testGetNewSession() {
        String sessionStr = UserSessions.getNewSessions(TestVal.fString + "1");
        
        Assert.assertEquals(sessionStr.length() > 0, true, "getNewSessions");
        
        Users user = UserSessions.getUser(sessionStr);
        
        Assert.assertEquals(user.getFirstName(), TestVal.fString + "1", "UserSessions.getUser");
    }
    
    @Test
    public void testGetUser() {
        Users user = UserSessions.getUser("session0");;
        
        Assert.assertEquals(user.getUsername(), "dummy");
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
