package request.service;

import java.util.Date;
import java.util.Map;

import request.model.Writer;

public class WriterRequest {
	private Writer writer;
	private String title;
	private String animal;
	private Date startDate;
	private Date endDate;
	private String postcode;
	private String roadAddress;
	private String info;

	public WriterRequest(Writer writer, String title, String animal, Date startDate, Date endDate, String postcode,
			String roadAddress, String info) {
		this.writer = writer;
		this.title = title;
		this.animal = animal;
		this.startDate = startDate;
		this.endDate = endDate;
		this.postcode = postcode;
		this.roadAddress = roadAddress;
		this.info = info;
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

	public Writer getWriter() {
		return writer;
	}
	public void setWriter(Writer writer) {
		this.writer = writer;
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
		
		if (postcode == null || postcode.trim().isEmpty()) {
			errors.put("postcode", true);
		}
		if (info == null || info.trim().isEmpty()) {
			errors.put("info", true);
		}
	}
	
	
	
	
	
}
