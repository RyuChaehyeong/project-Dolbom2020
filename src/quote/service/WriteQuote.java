package quote.service;

import java.util.Map;

public class WriteQuote {
	private String title;
	private String provider;
	private RequestSummary reqSum;
	private Integer price;
	private String location;
	private String info;

	public WriteQuote(String title, String provider, RequestSummary reqSum, Integer price, String location,
			String info) {
		this.title = title;
		this.provider = provider;
		this.reqSum = reqSum;
		this.price = price;
		this.location = location;
		this.info = info;
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

	public RequestSummary getReqSum() {
		return reqSum;
	}

	public void setReqSum(RequestSummary reqSum) {
		this.reqSum = reqSum;
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

	public void validate(Map<String, Boolean> errors) {
		if (title == null || title.isEmpty()) {
			errors.put("title", true);
		}

		if (price == null || price == 0) {
			errors.put("price", true);
		}

		if (location == null || location.isEmpty()) {
			errors.put("location", true);
		}

		if (info == null || info.isEmpty()) {
			errors.put("info", true);
		}

	}

}
