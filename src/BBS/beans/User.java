package BBS.beans;
import java.io.Serializable;

public class User implements Serializable{
	private static final long serialVersionUID = 1L;

	private int id;
	private String account;
	private String name;
	private String password;
	private String branch_id;
	private String department_id;
	private int is_stopped;

	public int getId(){
		return id;
	}

	public void setId(int id){
		this.id = id;
	}

	public String getAccount(){
		return account;
	}

	public void setAccount(String account){
		this.account = account;
	}

	public String getName(){
		return name;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getBranchId(){
		return branch_id;
	}

	public void setBranchId(String branch_id){
		this.branch_id = branch_id;
	}

	public String getDepartmentId(){
		return department_id;
	}

	public void setDepartmentId(String department_id){
		this.department_id = department_id;
	}

	public int getIsStopped(){
		return is_stopped;
	}

	public void setIsStopped(int is_stopped){
		this.is_stopped = is_stopped;
	}
}
