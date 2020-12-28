package mypage.service;

import java.util.List;

import contract.model.Contract;
import quote.service.Quote;

public class ContractPage {
	private int totalContract;
	private int currentPage;
	private List<Contract> contractList;
	private int totalPages;
	private int startPage;
	private int endPage;
	
	public ContractPage (int totalContract, int currentPage, int size, List<Contract> contractList) {
		this.totalContract = totalContract;
		this.currentPage = currentPage;
		this.contractList = contractList;
		
		if (totalContract != 0) {
			this.totalPages =  totalContract / size;
			if (totalContract % size > 0) {
				this.totalPages++;
			}
			this.startPage = (currentPage - 1) / 5 * 5 + 1;
			this.endPage = Math.min(startPage + 4, totalPages);
		}
	}
	
	public boolean hasNoContracts() {
		return totalContract == 0;
	}
	public boolean hasContracts() {
		return totalContract > 0;
	}

	public int getTotalContract() {
		return totalContract;
	}

	public void setTotalContract(int totalContract) {
		this.totalContract = totalContract;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public List<Contract> getContractList() {
		return contractList;
	}

	public void setContractList(List<Contract> contractList) {
		this.contractList = contractList;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}
	
	
}
