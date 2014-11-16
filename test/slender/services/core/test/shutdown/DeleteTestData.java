/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package slender.services.core.test.shutdown;

import com.slender.domain.UserProject;
import com.slender.domain.UserTask;
import com.slender.service.crud.CommentCrud;
import com.slender.service.crud.CompanyCrud;
import com.slender.service.crud.ProjectCrud;
import com.slender.service.crud.TaskCrud;
import com.slender.service.crud.UserCrud;
import com.slender.service.crud.UserProjectCrud;
import com.slender.service.crud.UserTaskCrud;
import com.slender.service.crud.impl.CommentCrudImpl;
import com.slender.service.crud.impl.CompanyCrudImpl;
import com.slender.service.crud.impl.ProjectCrudImpl;
import com.slender.service.crud.impl.SessionCrudImpl;
import com.slender.service.crud.impl.TaskCrudImpl;
import com.slender.service.crud.impl.UserCrudImpl;
import com.slender.service.crud.impl.UserProjectCrudImpl;
import com.slender.service.crud.impl.UserTaskCrudImpl;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import slender.services.core.test.TestVal;

/**
 *
 * @author Heinrich
 */
public class DeleteTestData {
    
    public DeleteTestData() {
    }
    
    @Test
    public void deleteData() {
        UserCrud userCrud = new UserCrudImpl();
        CompanyCrud compCrud = new CompanyCrudImpl();
        TaskCrud taskCrud = new TaskCrudImpl();
        SessionCrudImpl sessCrud = new SessionCrudImpl();
        ProjectCrud projCrud = new ProjectCrudImpl();
        UserProjectCrud upCrud = new UserProjectCrudImpl();
        UserTaskCrud utCrud = new UserTaskCrudImpl();
        CommentCrud commCrud = new CommentCrudImpl();
        
        // Remove Session
        sessCrud.removeById(TestVal.sessionId);
        
        // Remove Comment
        commCrud.removeById(TestVal.commentID);
        
        // Remove User Tasks
        UserTask ut = utCrud.getByPropertyName("userId", TestVal.user1ID);
        utCrud.remove(ut);
        
        ut = utCrud.getByPropertyName("userId", TestVal.user2ID);
        utCrud.remove(ut);
        
        // Remove User Projects
        UserProject up = upCrud.getByPropertyName("userId", TestVal.user1ID);
        upCrud.remove(up);
        
        up = upCrud.getByPropertyName("userId", TestVal.user2ID);
        upCrud.remove(up);
        
        // Remove User
        userCrud.removeById(TestVal.user1ID);
        userCrud.removeById(TestVal.user2ID);
        
        // Remove Task
        taskCrud.removeById(TestVal.taskID);
        
        // Remove Project
        projCrud.removeById(TestVal.projectID);
        
        // Remove Company
        compCrud.removeById(TestVal.compID);
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