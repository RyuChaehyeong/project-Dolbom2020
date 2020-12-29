package review.model;

public class Review {
	private Integer reviewNo;
	private Integer reqNo;
	private Integer quoNo;
	private Integer score;
	private String reviewer;
	private String target;
	private String comment;

	public Review(Integer reviewNo, Integer reqNo, Integer quoNo, Integer score, String reviewer, String target,
			String comment) {
		this.reviewNo = reviewNo;
		this.reqNo = reqNo;
		this.quoNo = quoNo;
		this.score = score;
		this.reviewer = reviewer;
		this.target = target;
		this.comment = comment;
	}

	public Integer getReviewNo() {
		return reviewNo;
	}

	public void setReviewNo(Integer reviewNo) {
		this.reviewNo = reviewNo;
	}

	public Integer getReqNo() {
		return reqNo;
	}

	public void setReqNo(Integer reqNo) {
		this.reqNo = reqNo;
	}

	public Integer getQuoNo() {
		return quoNo;
	}

	public void setQuoNo(Integer quoNo) {
		this.quoNo = quoNo;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public String getReviewer() {
		return reviewer;
	}

	public void setReviewer(String reviewer) {
		this.reviewer = reviewer;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	
	
	
	
}
