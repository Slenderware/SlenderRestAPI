/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package slender.webservice.rest.response.entities;

import com.slender.domain.Comment;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ws.rs.core.GenericEntity;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Heinrich
 */
@XmlRootElement
public class CommentResponse {
    private int id;
    private String comment;
    private Date createDate;

    public CommentResponse(Comment comment) {
        this.id = comment.getId();
        this.comment = comment.getComment();
        this.createDate = comment.getCreateDate();
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
        
        for(Comment c : comments) {
            responses.add(new CommentResponse(c));
        }
        
        return new GenericEntity<List<CommentResponse>>(responses) {};
    }
}