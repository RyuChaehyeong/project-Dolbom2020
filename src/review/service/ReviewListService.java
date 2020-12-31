package review.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import jdbc.ConnectionProvider;
import jdbc.JdbcUtil;
import request.service.Request;
import request.service.RequestPage;
import review.dao.ReviewDao;
import review.model.Review;

public class ReviewListService {
	private ReviewDao reviewDao = new ReviewDao();
	private int size = 10;
	
	public ReviewPage getReviewPage(int pageNo, String target) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			int totalReview = reviewDao.selectCount(conn, target);
			List<Review> reviewList = reviewDao.select(conn, pageNo, size, target);
			
			conn.commit();
			return new ReviewPage(totalReview, pageNo, size, reviewList);
					
		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException();
		} finally {
			JdbcUtil.close(conn);
		}
	}
	
	

}
