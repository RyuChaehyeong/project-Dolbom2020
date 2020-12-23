package member.model;

public class Member {
	private String member_id;
	private String name;
	private String password;
	private String address;
	private String animal;
	private String status;
	private double score;
	
	public Member(String member_id,
			String name,
			String password,
			String address,
			String animal,
			String status) {		
		this.member_id = member_id;
		this.name = name;
		this.password = password;
		this.address = address;
		this.animal = animal;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public double getScore() {
		return score;
	}
	public void setScore(double score) {
		this.score = score;
	}
	public boolean matchPassword(String password) {
		return this.password.equals(password);
	}
	
	
}
