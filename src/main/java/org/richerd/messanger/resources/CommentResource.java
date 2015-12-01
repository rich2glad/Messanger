/**
 * 
 */
package org.richerd.messanger.resources;

import java.util.List;

import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.richerd.messanger.model.Comment;
import org.richerd.messanger.model.Message;
import org.richerd.messanger.resources.beans.MessageFilterBean;
import org.richerd.messanger.service.CommentService;
import org.richerd.messanger.service.MessageService;

/**
 * @author HP
 *
 */
@Path("/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CommentResource {

	CommentService commentService= new CommentService();
	
	@GET
	public List<Comment> getComments(@PathParam("messageId") long messageId){
	
		
		return commentService.getAllComments(messageId);
	}
	
	@GET
	@Path("/{commentId}")
	public Comment getMessage(@PathParam("messageId") long messageId,@PathParam("commentId") long commentId){
		return commentService.getComment(messageId,commentId);
	}
	
	@POST
	public Comment addComment(@PathParam("messageId") long messageId,Comment comment){
		commentService.addComment(messageId,comment);
		return comment;
	}
	
	@PUT
	@Path("/{messageId}")
	public Comment updateMessage(@PathParam("messageId") long messageId,@PathParam("commentId") long commentId,Comment comment){
		comment.setId(commentId);
		commentService.updateComment(messageId,comment);
		return comment;
	}
	
	@DELETE
	@Path("/{messageId}")
	public void deleteMessage(@PathParam("messageId") long messageId,@PathParam("commentId") long commentId){
		commentService.removeComment(messageId,commentId);
	}
}
