package BBS.beans;

import java.io.Serializable;

public class Account implements Serializable {
	private static final long serialVersionUID = 1L;

	private String account;
	private String name;
	private int id;

	public int getId(){
		return id;
	}

	public void setId(int id){
		this.id = id;
	}

	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}