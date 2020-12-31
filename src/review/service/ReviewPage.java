package review.service;

import java.util.List;

import request.service.Request;
import review.model.Review;

public class ReviewPage {
	private int totalReview;
	private int currentPage;
	private List<Review> reviewList;
	private int totalPages;
	private int startPage;
	private int endPage;
	
	public ReviewPage (int totalReview, int currentPage, int size, List<Review> reviewList) {
		this.totalReview = totalReview;
		this.currentPage = currentPage;
		this.reviewList = reviewList;
		
		if (totalReview != 0) {
			this.totalPages =totalReview / size;
			if (totalReview % size > 0) {
				this.totalPages++;
			}
			this.startPage = (currentPage - 1) / 5 * 5 + 1;
			this.endPage = Math.min(startPage + 4, totalPages);
		}
	}
	
	public boolean hasNoReview() {
		return totalReview == 0;
	}
	public boolean hasReview() {
		return totalReview > 0;
	}

	public int getTotalReview() {
		return totalReview;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public List<Review> getReviewList() {
		return reviewList;
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
