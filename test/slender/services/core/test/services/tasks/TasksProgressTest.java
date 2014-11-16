/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package slender.services.core.test.services.tasks;

import org.testng.Assert;
import static org.testng.Assert.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import slender.services.core.tasks.TasksProgressService;
import slender.services.core.tasks.impl.TasksProgressServiceImpl;
import slender.services.core.test.TestVal;

/**
 *
 * @author Heinrich
 */
public class TasksProgressTest {
    
    public TasksProgressTest() {
    }
    
    @Test
    public void testGetProgress() {
        TasksProgressService service = new TasksProgressServiceImpl();
        int progress = service.getProgress(TestVal.taskID);
        
        Assert.assertEquals(progress, 6);
    }
    
    @Test
    public void testGetProgressPercentage() {
        TasksProgressService service = new TasksProgressServiceImpl();
        double progress = service.getProgressPercentage(TestVal.taskID);
        
        Assert.assertEquals(progress, 60.0);
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
