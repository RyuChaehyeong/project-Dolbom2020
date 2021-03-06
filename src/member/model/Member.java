package member.model;

public class Member {
	private String member_id;
	private String name;
	private String password;
	private String animal;
	private Integer status;
	private double score;
	private String email;
	private String phone;
	private String postcode;
	private String roadAddress;
	private String detailAddress;



	public Member(String member_id, String name, String password, String animal, Integer status,
			double score, String email, String phone, String postcode, String roadAddress, String detailAddress) {
		this.member_id = member_id;
		this.name = name;
		this.password = password;
		this.animal = animal;
		this.status = status;
		this.score = score;
		this.email = email;
		this.phone = phone;
		this.postcode = postcode;
		this.roadAddress = roadAddress;
		this.detailAddress = detailAddress;

	}
	
	
	public String getPostcode() {
		return postcode;
	}
	
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
	
	public String getRoadAddress() {
		return roadAddress;
	}
	
	public void setRoadAddress(String roadAddress) {
		this.roadAddress = roadAddress;
	}

	public String getDetailAddress() {
		return detailAddress;
	}

	public void setDetailAddress(String detailAddress) {
		this.detailAddress = detailAddress;
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
