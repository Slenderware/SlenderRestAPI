/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package slender.services.core.test.services.projects;

import org.testng.Assert;
import static org.testng.Assert.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import slender.services.core.projects.ProjectsProgressService;
import slender.services.core.projects.impl.ProjectsProgressServiceImpl;
import slender.services.core.test.TestVal;

/**
 *
 * @author Heinrich
 */
public class ProjectsProgressServiceTest {
    
    public ProjectsProgressServiceTest() {
    }

    @Test
    public void testProjectProgress() {
        ProjectsProgressService service = new ProjectsProgressServiceImpl();
        int progress = service.getProjectProgress(TestVal.projectID);
        
        Assert.assertEquals(progress, 6);
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
