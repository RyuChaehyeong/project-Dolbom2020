package review.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jdbc.JdbcUtil;
import request.service.Request;
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

	public int selectCount(Connection conn, String target) throws SQLException {
		String sql = "SELECT COUNT(*) FROM review WHERE target=?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, target);
			
			rs = pstmt.executeQuery();
			if (rs.next()) {
				return rs.getInt(1);
			}
			return 0;
		} finally {
			JdbcUtil.close(rs, pstmt);
		}
	}

	public List<Review> select(Connection conn, int pageNo, int size, String target) throws SQLException {
		String sql = "SELECT "
				+ "rn, "
				+ "review_no, "
				+ "req_no, "
				+ "quo_no, "
				+ "score, "
				+ "reviewer, "
				+ "target, "
				+ "text "
				+ "FROM ("
				+ "	SELECT review_no, "
				+ " 		   req_no, "
				+ "       	quo_no, "
				+ "        	score, "
				+ "       reviewer, "
				+ "      	target, "
				+ "        	text, "
				+ "        ROW_NUMBER() "
				+ "          OVER ( "
				+ "            ORDER BY "
				+ "            review_no "
				+ "            DESC) "
				+ "        rn "
				+ "  FROM review "
				+ "  WHERE target=?"
				+ ") WHERE rn  BETWEEN ? AND ?";
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, target);
			pstmt.setInt(2, (pageNo-1) * size + 1);
			pstmt.setInt(3, pageNo * size);
			
			rs = pstmt.executeQuery();
			List<Review> result = new ArrayList<Review>();
			while (rs.next()) {
				result.add(convertReview(rs));
			}
			return result;
		} finally {
			JdbcUtil.close(rs, pstmt);
		}
	}

	private Review convertReview(ResultSet rs) throws SQLException {
		return new Review(
				rs.getInt("review_no"), 
				rs.getInt("req_no"), 
				rs.getInt("quo_no"), 
				rs.getInt("score"), 
				rs.getString("reviewer"), 
				rs.getString("target"), 
				rs.getString("text"));
		
	}

}
