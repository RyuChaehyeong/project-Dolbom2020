package request.service;

import java.util.List;

public class RequestPage {
	private int totalReq;
	private int currentPage;
	private List<Request> reqList;
	private int totalPages;
	private int startPage;
	private int endPage;
	
	public RequestPage (int totalReq, int currentPage, int size, List<Request> reqList) {
		this.totalReq = totalReq;
		this.currentPage = currentPage;
		this.reqList = reqList;
		
		if (totalReq != 0) {
			this.totalPages = totalReq / size;
			if (totalReq % size > 0) {
				this.totalPages++;
			}
			this.startPage = (currentPage - 1) / 5 * 5 + 1;
			this.endPage = Math.min(startPage + 4, totalPages);
		}
	}
	
	public boolean hasNoRequests() {
		return totalReq == 0;
	}
	public boolean hasRequests() {
		return totalReq > 0;
	}

	public int getTotalReq() {
		return totalReq;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public List<Request> getReqList() {
		return reqList;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public int getStartPage() {
		return startPage;
	}

	public int getEndPage() {
		return endPage;
	}
	
}
