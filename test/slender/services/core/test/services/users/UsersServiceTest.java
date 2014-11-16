/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package slender.services.core.test.services.users;

import com.slender.app.factory.SessionFactory;
import com.slender.domain.Project;
import com.slender.domain.Session;
import com.slender.domain.Task;
import com.slender.domain.Users;
import com.slender.service.crud.impl.SessionCrudImpl;
import java.util.List;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import slender.services.core.test.TestVal;
import slender.services.core.users.UserListsService;
import slender.services.core.users.UsersService;
import slender.services.core.users.impl.UserListsServiceImpl;
import slender.services.core.users.impl.UsersServiceImpl;

/**
 *
 * @author Heinrich
 */
public class UsersServiceTest {
    
    public UsersServiceTest() {
    }

    private static String session;
    
    @Test
    public void testUserBySession() {
        UsersService service = new UsersServiceImpl();
        Users user = service.getUserBySession(session);
        
        Assert.assertEquals(user.getUsername().contains(TestVal.fString), true, "getUserBySession");
    }
    
    @Test
    public void testUserProjects() {
        UserListsService service = new UserListsServiceImpl();
        List<Project> list = service.getUserProjects(session);
        
        Assert.assertEquals(list.size(), 1);
        Assert.assertEquals(list.get(0).getProjectName(), TestVal.fString, "getUserProjects");
    }

    @Test
    public void testUserTasks() {
        UserListsService service = new UserListsServiceImpl();
        List<Task> list = service.getUserTasks(session);
        
        Assert.assertEquals(list.size(), 1);
        Assert.assertEquals(list.get(0).getTaskName(), TestVal.fString, "getUserTasks");
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        session = "coresessiontest";
        TestVal.sessionId = session;
        SessionCrudImpl crud = new SessionCrudImpl();
        SessionFactory fact = new SessionFactory();
        
        Session tmpSess = crud.findById(session);
        
        if(tmpSess == null) {
            Session sess = fact.getSession(session, TestVal.user1ID);
            crud.persist(sess);
        }
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
