package quote.service;

public class RequestSummary {
	private Integer reqNo;
	private String reqWriter;
	private String reqTitle;
	

	
	public RequestSummary(Integer reqNo, String reqWriter, String reqTitle) {
		this.reqNo = reqNo;
		this.reqWriter = reqWriter;
		this.reqTitle = reqTitle;
	}
	
	public Integer getReqNo() {
		return reqNo;
	}
	public void setReqNo(Integer reqNo) {
		this.reqNo = reqNo;
	}
	public String getReqWriter() {
		return reqWriter;
	}
	public void setReqWriter(String reqWriter) {
		this.reqWriter = reqWriter;
	}
	public String getReqTitle() {
		return reqTitle;
	}
	public void setReqTitle(String reqTitle) {
		this.reqTitle = reqTitle;
	}
	
	
	
}
