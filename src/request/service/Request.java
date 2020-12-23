package request.service;


import java.sql.Date;

import request.model.Writer;

public class Request {
	private Integer reqNo;
	private String writer_id;
	private String title;
	private String animal;
	private Date startDate;
	private Date endDate;
	private String local;
	private String info;
	public Request(Integer reqNo, String writer_id, String title, String animal, Date startDate, Date endDate,
			String local, String info) {
		super();
		this.reqNo = reqNo;
		this.writer_id = writer_id;
		this.title = title;
		this.animal = animal;
		this.startDate = startDate;
		this.endDate = endDate;
		this.local = local;
		this.info = info;
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
	public String getLocal() {
		return local;
	}
	public void setLocal(String local) {
		this.local = local;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	

	
}
