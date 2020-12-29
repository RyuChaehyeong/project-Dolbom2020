package member.model;

public class Member {
	private String member_id;
	private String name;
	private String password;
	private String address;
	private String animal;
	private Integer status;
	private double score;
	private String email;
	private String phone;
	

	public Member(String member_id, String name, String password, String address, String animal, Integer status,
			double score, String email, String phone) {
		this.member_id = member_id;
		this.name = name;
		this.password = password;
		this.address = address;
		this.animal = animal;
		this.status = status;
		this.score = score;
		this.email = email;
		this.phone = phone;
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getAnimal() {
		return animal;
	}
	public void setAnimal(String animal) {
		this.animal = animal;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public double getScore() {
		return score;
	}
	public void setScore(double score) {
		this.score = score;
	}
	
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public boolean matchPassword(String password) {
		return this.password.equals(password);
	}
	
	
}
