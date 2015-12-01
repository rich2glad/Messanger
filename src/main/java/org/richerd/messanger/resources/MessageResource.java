/**
 * 
 */
package org.richerd.messanger.resources;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.print.attribute.standard.Media;
import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.Response.Status;

import org.richerd.messanger.model.Message;
import org.richerd.messanger.resources.beans.MessageFilterBean;
import org.richerd.messanger.service.MessageService;

/**
 * @author Richerd
 *
 */

@Path("/messages")
@Produces(value ={MediaType.APPLICATION_JSON,MediaType.TEXT_XML})
@Consumes(value ={MediaType.APPLICATION_JSON,MediaType.TEXT_XML})
public class MessageResource {

	
	MessageService messageService= new MessageService();
	
	@GET
	public List<Message> getMessages(@BeanParam MessageFilterBean filterBean){
		System.out.println("First Called");
		if(filterBean.getYear()> 0){
			return messageService.getAllMessagesForYear(filterBean.getYear());
		}
		else if(filterBean.getStart()>=0 && filterBean.getSize()>0){
			return messageService.getAllMessagesPaginated(filterBean.getStart(), filterBean.getSize());
		}
		
		return messageService.getAllMessages();
	}
	
//	@GET
//	public List<Message> getMessagesanother(@BeanParam MessageFilterBean filterBean){
//		System.out.println("Second Called");
//		if(filterBean.getYear()> 0){
//			return messageService.getAllMessagesForYear(filterBean.getYear());
//		}
//		else if(filterBean.getStart()>=0 && filterBean.getSize()>0){
//			return messageService.getAllMessagesPaginated(filterBean.getStart(), filterBean.getSize());
//		}
//		
//		return messageService.getAllMessages();
//	}
	
	@GET
	@Path("/{messageId}")
	public Message getMessage(@PathParam("messageId") long messageId,@Context UriInfo uriInfo){
		Message message= messageService.getMessage(messageId);
		String uri = getURIforSelf(uriInfo, message);
		message.addLink(uri, "self");
		message.addLink(getURIforComments(uriInfo,message), "self");
		return message;
	}

	private String getURIforSelf(UriInfo uriInfo, Message message) {
		String uri= uriInfo.getBaseUriBuilder()
							.path(MessageResource.class)
							.path(Long.toString(message.getId()))
							.build().toString();
		return uri;
	}
	
	private String getURIforComments(UriInfo uriInfo, Message message) {
		String uri= uriInfo.getBaseUriBuilder()
							.path(MessageResource.class)
							.path(MessageResource.class, "getCommentResource")
							.path(CommentResource.class)
							.resolveTemplate("message",Long.toString(message.getId()))
							.build().toString();
		return uri;
	}
	
	@POST
	public Response addMessage(Message message, @Context UriInfo uri){
		Message newMessage = messageService.addMessage(message);
		String newId= String.valueOf(newMessage.getId());
		URI uritoSend = uri.getAbsolutePathBuilder().path(newId).build();
		return Response.created(uritoSend)
		.entity(newMessage)
		.build();
	}
	
	@PUT
	@Path("/{messageId}")
	public Message updateMessage(@PathParam("messageId") long messageId,Message message){
		message.setId(messageId);
		messageService.updateMessage(message);
		return message;
	}
	
	@DELETE
	@Path("/{messageId}")
	public void deleteMessage(@PathParam("messageId") long messageId){
		messageService.removeMessage(messageId);
	}
	
	@Path("/{messageId}/comments")
	public CommentResource getCommentResource(){
		return new CommentResource();
	}
	
}
