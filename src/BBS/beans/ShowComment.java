package BBS.beans;

import java.io.Serializable;
import java.sql.Timestamp;

public class ShowComment implements Serializable {
	private static final long serialVersionUID = 1L;

	private int comment_id;
	private String name;
	private int post_id;
	private String text;
	private Timestamp created_at;

	public int getComment_id() {
		return comment_id;
	}
	public void setComment_id(int comment_id) {
		this.comment_id = comment_id;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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