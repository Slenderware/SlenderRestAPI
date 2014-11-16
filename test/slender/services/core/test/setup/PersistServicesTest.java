/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package slender.services.core.test.setup;

import com.slender.app.factory.CommentFactory;
import com.slender.app.factory.CompanyFactory;
import com.slender.app.factory.ProjectFactory;
import com.slender.app.factory.TaskFactory;
import com.slender.app.factory.UsersFactory;
import com.slender.domain.Comment;
import com.slender.domain.Company;
import com.slender.domain.Project;
import com.slender.domain.Task;
import com.slender.domain.Users;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import slender.services.core.comments.CommentsService;
import slender.services.core.comments.impl.CommentsServiceImpl;
import slender.services.core.company.CompanyService;
import slender.services.core.company.impl.CompanyServiceImpl;
import slender.services.core.projects.ProjectsService;
import slender.services.core.projects.impl.ProjectsServiceImpl;
import slender.services.core.tasks.TasksProgressService;
import slender.services.core.tasks.TasksService;
import slender.services.core.tasks.impl.TasksProgressServiceImpl;
import slender.services.core.tasks.impl.TasksServiceImpl;
import slender.services.core.test.TestVal;
import slender.services.core.users.UsersService;
import slender.services.core.users.impl.UsersServiceImpl;

/**
 *
 * @author Heinrich
 */
public class PersistServicesTest {
    
    private CompanyService compService = new CompanyServiceImpl();
    private UsersService userService = new UsersServiceImpl();
    private TasksService taskService = new TasksServiceImpl();
    private ProjectsService projService = new ProjectsServiceImpl();
    private CommentsService commService = new CommentsServiceImpl();

    public PersistServicesTest() {
        
    }
    
    @Test
    public void addMainEntities() {   
        CompanyFactory compFact = new CompanyFactory();
        UsersFactory userFact = new UsersFactory();
        TaskFactory taskFact = new TaskFactory();
        ProjectFactory projFact = new ProjectFactory();
        CommentFactory commFact = new CommentFactory();

        // Add Company
        Company comp = compFact.getCompany(TestVal.fString, 0);
        comp.setAdminUser(null);
        Company newComp = compService.addCompany(comp);
        TestVal.compID = newComp.getId();

        Assert.assertEquals(newComp.getCompanyName(), TestVal.fString, "addCompany");

        // Add Admin User
        Users adminUser = userFact.getUsers(TestVal.fString + "1", TestVal.fString + "1", TestVal.fString + "1", TestVal.fString + "1", TestVal.fString + "1", 1, TestVal.compID);
        Users newAdminUser = userService.addAdminUser(adminUser);
        TestVal.user1ID = newAdminUser.getId();

        Assert.assertEquals(newAdminUser.getFirstName(), TestVal.fString + "1", "addAdminUser");

        // Add User
        Users user2 = userFact.getUsers(TestVal.fString + "2", TestVal.fString + "2", TestVal.fString + "2", TestVal.fString + "2", TestVal.fString + "2", 2, TestVal.compID);
        Users newUser2 = userService.addUser(user2);
        TestVal.user2ID = newUser2.getId();

        Assert.assertEquals(newUser2.getFirstName(), TestVal.fString + "2", "addUser");

        // Add Project
        Project proj = projFact.getProject(TestVal.user1ID, TestVal.user1ID, TestVal.fString, TestVal.fString, TestVal.eDate, TestVal.eDate);
        Project newProj = projService.addProject(proj);
        TestVal.projectID = newProj.getId();

        Assert.assertEquals(newProj.getProjectName(), TestVal.fString, "addProject");

        // Add Task
        Task task = taskFact.getTask(TestVal.projectID, TestVal.fString, TestVal.fString, TestVal.eDate, TestVal.eDate, TestVal.eDate, TestVal.eDate, 10, 1);
        Task newTask = taskService.addTask(task);
        TestVal.taskID = newTask.getId();

        Assert.assertEquals(newTask.getTaskName(), TestVal.fString, "getTask");

        // Add Comment to Project
        Comment comment = commFact.getComment(TestVal.fString, TestVal.projectID, 0, TestVal.user2ID);
        comment.setTaskId(null);
        Comment newComm = commService.addComment(comment);
        TestVal.commentID = newComm.getId();

        Assert.assertEquals(newComm.getComment(), TestVal.fString, "getComment");
        
        // Add Comment to Task
        Comment taskComm = commFact.getComment(TestVal.fString, 0, TestVal.taskID, TestVal.user1ID);
        taskComm.setProjectId(null);
        Comment newTaskCommComm = commService.addComment(taskComm);

        Assert.assertEquals(newTaskCommComm.getComment(), TestVal.fString, "getComment");
    }

    @Test(dependsOnMethods = {"addMainEntities"})
    public void addAdditional() {
        TasksProgressService service = new TasksProgressServiceImpl();
        service.addProgress(TestVal.taskID, TestVal.user1ID, 4);
        service.addProgress(TestVal.taskID, TestVal.user2ID, 2);
        
        UsersService userService = new UsersServiceImpl();
        userService.addUserToProject(TestVal.user1ID, TestVal.projectID);
        userService.addUserToProject(TestVal.user2ID, TestVal.projectID);
        
        userService.addUserToTask(TestVal.user1ID, TestVal.taskID);
        userService.addUserToTask(TestVal.user2ID, TestVal.taskID);
    }
    
    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
        // remove data
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
    }

    @AfterMethod
    public void tearDownMethod() throws Exception {
    }
}
