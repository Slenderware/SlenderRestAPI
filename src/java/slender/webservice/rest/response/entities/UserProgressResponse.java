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
import slender.services.core.users.UsersService;
import slender.services.core.users.impl.UsersServiceImpl;

/**
 *
 * @author Heinrich
 */
@XmlRootElement
public class UserProgressResponse {
    private Integer id;
    private String username;
    private int progress;

    public UserProgressResponse(Users user, int progress) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.progress = progress;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public static GenericEntity<List<UserProgressResponse>> getResponseEntity(List<Users> entities, Integer taskId) {
        List<UserProgressResponse> responses = new ArrayList<UserProgressResponse>();
        UsersService service = new UsersServiceImpl();
        String sessionStr;
        int progress;
        
        for(Users e : entities) {
            sessionStr = UserSessions.getNewSessions(e.getUsername());
            progress = service.getTimeSpentForTask(sessionStr, taskId);
            responses.add(new UserProgressResponse(e, progress));
        }

        return new GenericEntity<List<UserProgressResponse>>(responses) {};
    }
}
