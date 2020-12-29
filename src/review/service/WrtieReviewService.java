package review.service;

import java.sql.Connection;
import java.sql.SQLException;

import contract.dao.ContractDao;
import contract.model.Contract;
import jdbc.ConnectionProvider;
import jdbc.JdbcUtil;
import member.dao.MemberDao;
import review.dao.ReviewDao;
import review.model.Review;

public class WrtieReviewService {
	private ReviewDao reviewDao = new ReviewDao();
	private ContractDao contractDao = new ContractDao();
	private MemberDao memberDao = new MemberDao();
		
	public int write(WriteReview writeRv) throws SQLException {
		Connection conn = null;
		
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			Review review = toReview(writeRv);
			Review savedReview = reviewDao.insert(conn, review);
			
			if (savedReview == null) {
				throw new RuntimeException("fail to insert review");
			}
			
			int quoteNum = writeRv.getQuoNum();
			String userId = writeRv.getReviewer();
			
			Contract contract = contractDao.selectByQuoNum(conn, quoteNum);
			
			if (contract.getCustomerId().equals(userId)) {
				contractDao.updateCReview(conn, quoteNum);				
			} else if (contract.getProviderId().equals(userId)) {
				contractDao.updatePReview(conn, quoteNum);
			}
			
			memberDao.updateScore(conn, writeRv.getTarget());
			
			
			conn.commit();
			return savedReview.getReviewNo();
			
			
		} finally  {
			JdbcUtil.close(conn);
		}
	}

	private Review toReview(WriteReview writeRv) {
		return new Review(
				0, 
				writeRv.getReqNum(), 
				writeRv.getQuoNum(), 
				writeRv.getScore(), 
				writeRv.getReviewer(), 
				writeRv.getTarget(), 
				writeRv.getComment());
	}

}
