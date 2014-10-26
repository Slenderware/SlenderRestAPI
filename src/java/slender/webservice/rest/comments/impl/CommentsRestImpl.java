/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package slender.webservice.rest.comments.impl;

import com.google.common.collect.Lists;
import com.slender.domain.Comment;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.Response;
import javax.xml.bind.annotation.XmlRootElement;
import slender.webservice.rest.comments.CommentsRest;
import slender.webservice.services.comments.CommentsService;
import slender.webservice.services.comments.impl.CommentsServiceImpl;

/**
 *
 * @author Heinrich
 */
@Path("/comments")
public class CommentsRestImpl implements CommentsRest {

    @POST
    @Path("getCommentsOfTask")
    @Override
    public Response getCommentsOfTask(@FormParam("id") int taskId) {
        CommentsService service = new CommentsServiceImpl();
        List<Comment> comments = service.getComments(taskId);
        
        return Response.ok(getResponseEntity(comments)).build();
    }
    
    private GenericEntity<List<CommentResponse>> getResponseEntity(List<Comment> comments) {
        List<CommentResponse> responses = new ArrayList<CommentResponse>();
        
        for(Comment c : comments) {
            responses.add(new CommentResponse(c));
        }
        
        return new GenericEntity<List<CommentResponse>>(Lists.newArrayList(responses)) {};
    }
    
    @XmlRootElement
    private class CommentResponse {
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
    }
}
