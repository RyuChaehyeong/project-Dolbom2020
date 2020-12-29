package review.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jdbc.JdbcUtil;
import review.model.Review;

public class ReviewDao {

	public Review insert(Connection conn, Review review) throws SQLException {
		String sql = "INSERT INTO review (req_no, quo_no, score, reviewer, target, text) "
				+ "VALUES (?, ?, ?, ?, ?, ?)";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			int col = 1;
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(col++, review.getReqNo());
			pstmt.setInt(col++, review.getQuoNo());
			pstmt.setInt(col++, review.getScore());
			pstmt.setString(col++, review.getReviewer());
			pstmt.setString(col++, review.getTarget());
			pstmt.setString(col++, review.getComment());
			
			int cnt = pstmt.executeUpdate();
			
			if (cnt == 1) {
				rs = pstmt.getGeneratedKeys();
				int key = 0;
				
				if (rs.next()) {
					key = rs.getInt("review_no");
				}
				
				return new Review(
						key, 
						review.getReqNo(), 
						review.getQuoNo(), 
						review.getScore(), 
						review.getReviewer(), 
						review.getTarget(), 
						review.getComment());
			} else {
				return null;
			}
		} finally {
			JdbcUtil.close(rs, pstmt);
		}
	}

}
