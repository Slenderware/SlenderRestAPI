/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package slender.webservice.rest.response.entities;

import com.slender.domain.Comment;
import com.slender.domain.Users;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ws.rs.core.GenericEntity;
import javax.xml.bind.annotation.XmlRootElement;
import slender.services.core.users.UsersService;
import slender.services.core.users.impl.UsersServiceImpl;

/**
 *
 * @author Heinrich
 */
@XmlRootElement
public class CommentResponse {
    private int id;
    private String comment;
    public String username;
    private Date createDate;

    public CommentResponse(Comment comment, String username) {
        this.id = comment.getId();
        this.comment = comment.getComment();
        this.createDate = comment.getCreateDate();
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
    
    public static GenericEntity<List<CommentResponse>> getResponseEntity(List<Comment> comments) {
        List<CommentResponse> responses = new ArrayList<CommentResponse>();
        
        UsersService service = new UsersServiceImpl();
        Users user;
        for(Comment c : comments) {
            user = service.getUser(c.getUserId());
            responses.add(new CommentResponse(c, user.getUsername()));
        }
        
        return new GenericEntity<List<CommentResponse>>(responses) {};
    }
}