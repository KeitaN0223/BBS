package BBS.beans;

import java.io.Serializable;

public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;
	private String account;
	private String name;
	private String password;
	private int branch_id;
	private int department_id;
	private int is_stopped;
	public int getId() {
		return id;
	}
	public void setId(int id) {
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getBranch_id() {
		return branch_id;
	}
	public void setBranch_id(int branch_id) {
		this.branch_id = branch_id;
	}
	public int getDepartment_id() {
		return department_id;
	}
	public void setDepartment_id(int department_id) {
		this.department_id = department_id;
	}
	public int getIs_stopped() {
		return is_stopped;
	}
	public void setIs_stopped(int is_stopped) {
		this.is_stopped = is_stopped;
	}


}
