/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package slender.webservice.rest.response.entities;

import com.slender.domain.Users;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.core.GenericEntity;
import javax.xml.bind.annotation.XmlRootElement;
import slender.services.core.accounts.session.UserSessions;
import slender.services.core.users.UsersProgressService;
import slender.services.core.users.impl.UsersProgressServiceImpl;

/**
 *
 * @author Heinrich
 */
@XmlRootElement
public class UserProgressResponse extends UserResponse {
    private int progressHours;
    private double progressPercentage;

    public UserProgressResponse(int progressHours, double progressPercentage, Users user) {
        super(user);
        this.progressHours = progressHours;
        this.progressPercentage = progressPercentage;
    }

    public int getProgressHours() {
        return progressHours;
    }

    public void setProgressHours(int progressHours) {
        this.progressHours = progressHours;
    }

    public double getProgressPercentage() {
        return progressPercentage;
    }

    public void setProgressPercentage(double progressPercentage) {
        this.progressPercentage = progressPercentage;
    }

    public static GenericEntity<List<UserProgressResponse>> getResponseEntity(List<Users> entities, Integer taskId) {
        List<UserProgressResponse> responses = new ArrayList<UserProgressResponse>();
        UsersProgressService service = new UsersProgressServiceImpl();
        
        String sessionStr;
        int progressHours;
        double progressPerc;
        for(Users e : entities) {
            sessionStr = UserSessions.getNewSessions(e.getUsername());
            progressHours = service.getTimeSpentForTask(sessionStr, taskId);
            progressPerc = service.getPercentageSpentForTask(sessionStr, taskId);
            responses.add(new UserProgressResponse(progressHours, progressPerc, e));
        }

        return new GenericEntity<List<UserProgressResponse>>(responses) {};
    }
}
