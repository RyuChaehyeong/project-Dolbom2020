package member.service;

import java.util.Map;

public class JoinRequest {
	private String member_id;
	private String name;
	private String password;
	private String confirmPwd;
	private String animal;
	private Integer status;
	private String email;
	private String phone;
	private String postcode;
	private String roadAddress;
	private String detailAddress;
	
	
	public String getDetailAddress() {
		return detailAddress;
	}
	public void setDetailAddress(String detailAddress) {
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
	public String getConfirmPwd() {
		return confirmPwd;
	}
	public void setConfirmPwd(String confirmPwd) {
		this.confirmPwd = confirmPwd;
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
	public void validate(Map<String, Boolean> errors) {
		checkEmpty(errors, member_id, "id");
		checkEmpty(errors, name, "name");
		checkEmpty(errors, password, "password");
		checkEmpty(errors, confirmPwd, "confirmPwd");
		checkEmpty(errors, phone, "phone");
		checkEmpty(errors, email, "email");
		checkEmpty(errors, animal, "animal");
		checkEmpty(errors, status+"", "status");
		checkEmpty(errors, postcode, "postcode");
		checkEmpty(errors, detailAddress, "detailAddress");

		
		if (!errors.containsKey("confirmPwd")) {
			if (!isPasswordEqualToConfirm()) {
				errors.put("notMatch", Boolean.TRUE);
			}
		}
	}
	private void checkEmpty(Map<String, Boolean> errors, String value, String fieldName) {
		if (value == null || value.isEmpty()) {
			errors.put(fieldName, Boolean.TRUE);
		}
		
	}
	private boolean isPasswordEqualToConfirm() {
		return password != null && password.equals(confirmPwd);
	}
	
}
