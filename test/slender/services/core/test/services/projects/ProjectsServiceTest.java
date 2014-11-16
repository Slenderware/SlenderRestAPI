/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package slender.services.core.test.services.projects;

import com.slender.domain.Comment;
import com.slender.domain.Project;
import com.slender.domain.Task;
import com.slender.domain.Users;
import java.util.List;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import slender.services.core.projects.ProjectListsService;
import slender.services.core.projects.ProjectsService;
import slender.services.core.projects.impl.ProjectListsServiceImpl;
import slender.services.core.projects.impl.ProjectsServiceImpl;
import slender.services.core.test.TestVal;

/**
 *
 * @author Heinrich
 */
public class ProjectsServiceTest {
    
    public ProjectsServiceTest() {
    }

    @Test
    public void testProject() {
        ProjectsService service = new ProjectsServiceImpl();
        Project project = service.getProject(TestVal.projectID);
        
        Assert.assertEquals(project.getProjectName(), TestVal.fString, "getProject");
    }
    
    @Test
    public void testProjectUsers() {
        ProjectListsService service = new ProjectListsServiceImpl();
        List<Users> users = service.getProjectUsers(TestVal.projectID);
        
        Assert.assertEquals(users.size(), 2, "getProjectUsers");
        Assert.assertEquals(users.get(0).getUsername().contains(TestVal.fString), true, "getProjectUsers");
    }
    
    @Test
    public void testProjectTasks() {
        ProjectListsService service = new ProjectListsServiceImpl();
        List<Task> tasks = service.getProjectTasks(TestVal.projectID);
        
        Assert.assertEquals(tasks.size(), 1, "getProjectTasks");
        Assert.assertEquals(tasks.get(0).getTaskName(), TestVal.fString, "getProjectTasks");
    }
    
    @Test
    public void testProjectComments() {
        ProjectListsService service = new ProjectListsServiceImpl();
        List<Comment> comments = service.getProjectComments(TestVal.projectID);
        
        Assert.assertEquals(comments.size(), 1, "getProjectComments");
        Assert.assertEquals(comments.get(0).getComment(), TestVal.fString, "getProjectComments");
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
