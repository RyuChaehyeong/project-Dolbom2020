package request.service;

import java.sql.Date;
import java.util.Map;

public class ModifyRequest {
	private Integer reqNo;
	private String writer_id;
	private String title;
	private String animal;
	private Date startDate;
	private Date endDate;
	private String postcode;
	private String roadaddress;
	private String info;
	


	public ModifyRequest(Integer reqNo, String writer_id, String title, String animal, Date startDate, Date endDate,
			String postcode, String roadaddress, String info) {
		this.reqNo = reqNo;
		this.writer_id = writer_id;
		this.title = title;
		this.animal = animal;
		this.startDate = startDate;
		this.endDate = endDate;
		this.postcode = postcode;
		this.roadaddress = roadaddress;
		this.info = info;
	}

	
	
	public String getPostcode() {
		return postcode;
	}



	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}



	public String getRoadaddress() {
		return roadaddress;
	}



	public void setRoadaddress(String roadaddress) {
		this.roadaddress = roadaddress;
	}



	public Integer getReqNo() {
		return reqNo;
	}

	public void setReqNo(Integer reqNo) {
		this.reqNo = reqNo;
	}

	public String getWriter_id() {
		return writer_id;
	}

	public void setWriter_id(String writer_id) {
		this.writer_id = writer_id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAnimal() {
		return animal;
	}

	public void setAnimal(String animal) {
		this.animal = animal;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}


	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public void validate(Map<String, Boolean> errors) {
		if (title == null || title.trim().isEmpty()) {
			errors.put("title", true);
		}
		if (animal.equals("돌봄동물을 선택하세요.")) {
			errors.put("animal", true);
		}
		if (postcode == null || postcode.isEmpty()) {
			errors.put("postcode", true);
		}
		if (info == null || info.isEmpty()) {
			errors.put("info", true);
		}
	}
	

}
