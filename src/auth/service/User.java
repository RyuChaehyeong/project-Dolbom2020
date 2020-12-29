package auth.service;

public class User {
	private String member_id;
	private String name;
	private Integer status;
	
	public User(String member_id, String name, Integer status) {
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
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	
}
