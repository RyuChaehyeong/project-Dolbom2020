package quote.service;

public class Quote {
	private Integer quoteNo;
	private String title;
	private String provider;
	private Integer reqNo;
	private String reqWriter;
	private Integer price;
	private String location;
	private String info;
	private Integer complete;
	
	public Quote(Integer quoteNo, String title, String provider, Integer reqNo, String reqWriter, Integer price,
			String location, String info, Integer complete) {
		this.quoteNo = quoteNo;
		this.title = title;
		this.provider = provider;
		this.reqNo = reqNo;
		this.reqWriter = reqWriter;
		this.price = price;
		this.location = location;
		this.info = info;
		this.complete = complete;
	}
	public Quote(Integer quoteNo, String title, String provider, Integer reqNo, String reqWriter, Integer price,
			String location, String info) {
		this.quoteNo = quoteNo;
		this.title = title;
		this.provider = provider;
		this.reqNo = reqNo;
		this.reqWriter = reqWriter;
		this.price = price;
		this.location = location;
		this.info = info;
	}
	
	
	public Integer getComplete() {
		return complete;
	}
	public void setComplete(Integer complete) {
		this.complete = complete;
	}
	public Integer getQuoteNo() {
		return quoteNo;
	}
	public void setQuoteNo(Integer quoteNo) {
		this.quoteNo = quoteNo;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getProvider() {
		return provider;
	}
	public void setProvider(String provider) {
		this.provider = provider;
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
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	
	
	
}
