package auth.service;

public class User {
	private String member_id;
	private String name;
	private String status;
	
	public User(String member_id, String name, String status) {
		this.member_id = member_id;
		this.name = name;
		this.status = status;
	}
	
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
