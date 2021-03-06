package BBS.beans;

import java.io.Serializable;
import java.sql.Timestamp;

public class Comment implements Serializable {
	private static final long serialVersionUID = 1L;

	private int comment_id;
	private int user_id;
	private int post_id;
	private String text;
	private Timestamp created_at;
	public int getId() {
		return comment_id;
	}
	public void setId(int comment_id) {
		this.comment_id = comment_id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public int getPost_id() {
		return post_id;
	}
	public void setPost_id(int post_id) {
		this.post_id = post_id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public Timestamp getCreated_at() {
		return created_at;
	}
	public void setCreated_at(Timestamp created_at) {
		this.created_at = created_at;
	}
}