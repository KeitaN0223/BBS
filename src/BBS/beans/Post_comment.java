package BBS.beans;

import java.io.Serializable;
import java.sql.Timestamp;

public class Post_comment implements Serializable {
	private static final long serialVersionUID = 1L;

	private String subject;
	private String post_text;
	private Timestamp post_created_at;
	private String post_name;
	private String comment;
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getPost_text() {
		return post_text;
	}
	public void setPost_text(String post_text) {
		this.post_text = post_text;
	}
	public String getPost_name() {
		return post_name;
	}
	public void setPost_name(String post_name) {
		this.post_name = post_name;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public Timestamp getPost_created_at() {
		return post_created_at;
	}
	public void setPost_created_at(Timestamp post_created_at) {
		this.post_created_at = post_created_at;
	}
}
