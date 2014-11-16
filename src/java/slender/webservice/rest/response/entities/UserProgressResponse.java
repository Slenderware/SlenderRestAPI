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
 * @author Heinrich van den Ende
 * 
 *  The MIT License (MIT)

    Copyright © 2014 Slenderware

    Permission is hereby granted, free of charge, to any person obtaining a copy
    of this software and associated documentation files (the "Software"), to deal
    in the Software without restriction, including without limitation the rights
    to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
    copies of the Software, and to permit persons to whom the Software is
    furnished to do so, subject to the following conditions:

    The above copyright notice and this permission notice shall be included in all
    copies or substantial portions of the Software.

    THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
    IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
    FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
    AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
    LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
    OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
    SOFTWARE.
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

        int progressHours;
        double progressPerc;
        for(Users e : entities) {
            progressHours = service.getTimeSpentForTask(e.getId(), taskId);
            progressPerc = service.getPercentageSpentForTask(e.getId(), taskId);
            responses.add(new UserProgressResponse(progressHours, progressPerc, e));
        }

        return new GenericEntity<List<UserProgressResponse>>(responses) {};
    }
}
