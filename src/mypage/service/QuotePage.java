package mypage.service;

import java.util.List;

import quote.service.Quote;

public class QuotePage {
	private int totalQuote;
	private int currentPage;
	private List<Quote> quoteList;
	private int totalPages;
	private int startPage;
	private int endPage;
	
	public QuotePage (int totalQuote, int currentPage, int size, List<Quote> quoteList) {
		this.totalQuote = totalQuote;
		this.currentPage = currentPage;
		this.quoteList = quoteList;
		
		if (totalQuote != 0) {
			this.totalPages =  totalQuote / size;
			if (totalQuote % size > 0) {
				this.totalPages++;
			}
			this.startPage = (currentPage - 1) / 5 * 5 + 1;
			this.endPage = Math.min(startPage + 4, totalPages);
		}
	}
	
	public boolean hasNoQuotes() {
		return totalQuote == 0;
	}
	public boolean hasQuotes() {
		return totalQuote > 0;
	}

	public int getTotalQuote() {
		return totalQuote;
	}

	public void setTotalQuote(int totalQuote) {
		this.totalQuote = totalQuote;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public List<Quote> getQuoteList() {
		return quoteList;
	}

	public void setQuoteList(List<Quote> quoteList) {
		this.quoteList = quoteList;
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
