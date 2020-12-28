package contract.model;

public class Contract {
	private int contractNum;
	private int reqNum;
	private int quoNum;
	private String customerId;
	private String providerId;
	private int customerReview;
	private int providerReview;



	public Contract(int contractNum, int reqNum, int quoNum, String customerId, String providerId, int customerReview,
			int providerReview) {
		this.contractNum = contractNum;
		this.reqNum = reqNum;
		this.quoNum = quoNum;
		this.customerId = customerId;
		this.providerId = providerId;
		this.customerReview = customerReview;
		this.providerReview = providerReview;
	}

	public int getContractNum() {
		return contractNum;
	}

	public void setContractNum(int contractNum) {
		this.contractNum = contractNum;
	}

	public int getReqNum() {
		return reqNum;
	}

	public void setReqNum(int reqNum) {
		this.reqNum = reqNum;
	}

	public int getQuoNum() {
		return quoNum;
	}

	public void setQuoNum(int quoNum) {
		this.quoNum = quoNum;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getProviderId() {
		return providerId;
	}

	public void setProviderId(String providerId) {
		this.providerId = providerId;
	}

	public int getCustomerReview() {
		return customerReview;
	}

	public void setCustomerReview(int customerReview) {
		this.customerReview = customerReview;
	}

	public int getProviderReview() {
		return providerReview;
	}

	public void setProviderReview(int providerReview) {
		this.providerReview = providerReview;
	}

	
	
}
