package review.service;

import java.util.Map;

public class WriteReview {
	private Integer reqNum;
	private Integer quoNum;
	private Integer score;
	private String comment;
	private String reviewer;
	private String target;
	
	public WriteReview(Integer reqNum, Integer quoNum, Integer score, String comment, String reviewer, String target) {
		this.reqNum = reqNum;
		this.quoNum = quoNum;
		this.score = score;
		this.comment = comment;
		this.reviewer = reviewer;
		this.target = target;
	}
	
	public Integer getReqNum() {
		return reqNum;
	}
	public void setReqNum(Integer reqNum) {
		this.reqNum = reqNum;
	}
	public Integer getQuoNum() {
		return quoNum;
	}
	public void setQuoNum(Integer quoNum) {
		this.quoNum = quoNum;
	}
	public Integer getScore() {
		return score;
	}
	public void setScore(Integer score) {
		this.score = score;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
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

	public void validate(Map<String, Boolean> errors) {
		if (comment == null || comment.isEmpty()) {
			errors.put("comment", true);
		}
		
	}
	
	
	
}
