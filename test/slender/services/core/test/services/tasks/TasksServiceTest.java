/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package slender.services.core.test.services.tasks;

import com.slender.domain.Comment;
import com.slender.domain.Task;
import com.slender.domain.Users;
import java.io.File;
import java.util.List;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import slender.services.core.tasks.TaskListsService;
import slender.services.core.tasks.TasksProgressService;
import slender.services.core.tasks.TasksService;
import slender.services.core.tasks.impl.TaskListsServiceImpl;
import slender.services.core.tasks.impl.TasksProgressServiceImpl;
import slender.services.core.tasks.impl.TasksServiceImpl;
import slender.services.core.test.TestVal;

/**
 *
 * @author Heinrich
 */
public class TasksServiceTest {
    
    public TasksServiceTest() {
    }

    @Test
    public void testGetTask() {
        TasksService service = new TasksServiceImpl();
        Task task = service.getTask(TestVal.taskID);
        
        Assert.assertEquals(task.getTaskName(), TestVal.fString, "getTask");
    }
    
    @Test
    public void testTaskComments() {
        TaskListsService service = new TaskListsServiceImpl();
        List<Comment> list = service.getTaskComments(TestVal.taskID);
        
        Assert.assertEquals(list.size(), 1, "getTaskComments");
        Assert.assertEquals(list.get(0).getComment(), TestVal.fString, "getTaskComments");
    }
    
    @Test
    public void testTaskUsers() {
        TaskListsService service = new TaskListsServiceImpl();
        List<Users> list = service.getTaskUsers(TestVal.taskID);
        
        Assert.assertEquals(list.size(), 2, "getTaskUsers");
        Assert.assertEquals(list.get(0).getUsername().contains(TestVal.fString), true, "getTaskUsers");
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
