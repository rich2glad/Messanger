package org.richerd.messanger.model;

import java.util.Date;

public class Comment {

	private long id;
	private String message;
	private Date creataed;
	private String author;
	
	public Comment() {
		// TODO Auto-generated constructor stub
	}
	
	public Comment(long id,String message,String author){
		this.id=id;
		this.message=message;
		this.author=author;
		this.creataed=new Date();
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Date getCreataed() {
		return creataed;
	}
	public void setCreataed(Date creataed) {
		this.creataed = creataed;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	
	
}
