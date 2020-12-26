package quote.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jdbc.JdbcUtil;
import quote.service.Quote;
import quote.service.RequestSummary;

public class QuoteDao {

	public Quote insert(Connection conn, Quote quote) throws SQLException {
		String sql = "INSERT INTO quote (quote_title, provider_id, req_no, req_writer, req_title, price, location, info ) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			int c = 1;
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(c++, quote.getTitle());
			pstmt.setString(c++, quote.getProvider());
			pstmt.setInt(c++, quote.getReqSum().getReqNo());
			pstmt.setString(c++, quote.getReqSum().getReqWriter());
			pstmt.setString(c++, quote.getReqSum().getReqTitle());
			pstmt.setInt(c++, quote.getPrice());
			pstmt.setString(c++, quote.getLocation());
			pstmt.setString(c++, quote.getInfo());
			
			int cnt = pstmt.executeUpdate();
			
			if (cnt == 1) {
				rs = pstmt.getGeneratedKeys();
				int key = 0;
				int complete = 0;
				
				if (rs.next()) {
					key = rs.getInt("quote_no");
					complete = rs.getInt("complete");
				}
				
				return new Quote(
						key, quote.getTitle(), 
						quote.getProvider(), 
						new RequestSummary(
								quote.getReqSum().getReqNo(), 
								quote.getReqSum().getReqWriter(), 
								quote.getReqSum().getReqTitle()),								
						quote.getPrice(), 
						quote.getLocation(), 
						quote.getInfo(), 
						complete);
			} else {
				return null;
			}
		} finally {
			JdbcUtil.close(rs, pstmt);
		}
	}

	public List<String> selectByReq_no(Connection conn, int reqNum) throws SQLException {
		String sql = "SELECT provider_id FROM quote WHERE req_no=?";
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, reqNum);
			ResultSet rs = pstmt.executeQuery();
			
			List<String> providerList = new ArrayList<String>();
			while (rs.next()) {
				providerList.add(rs.getString(1));
			}
			
			return providerList;
		}
	}

	public int selectCountQuoToCustomer(Connection conn, String writerId) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		String sql = "SELECT COUNT(*) FROM quote WHERE req_writer=? AND complete=0";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, writerId);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				return rs.getInt(1);
			}
			return 0;
		} finally {
			JdbcUtil.close(rs, pstmt);
		}
	}

	public List<Quote> selectQuoToCustomer(Connection conn, int qpageNo, int size, String writerId) throws SQLException {
		String sql = "SELECT "
				+ "rn, "
				+ "quote_no, "
				+ "quote_title, "
				+ "provider_id, "
				+ "req_no, "
				+ "req_writer, "
				+ "req_title, "
				+ "price, "
				+ "location, "
				+ "info, "
				+ "complete "
				+ "FROM ("
				+ "	SELECT quote_no, "
				+ " 		quote_title, "
				+ "       provider_id, "
				+ "       req_no, "
				+ "       req_writer, "
				+ "			req_title, "
				+ "     	 price, "
				+ "     	  location, "
				+ "			info, "
				+ "			complete,  "
				+ "        ROW_NUMBER() "
				+ "          OVER ( "
				+ "            ORDER BY "
				+ "            quote_no "
				+ "            DESC) "
				+ "        rn "
				+ "  FROM quote WHERE req_writer=? AND complete=0"
				+ ") WHERE rn  BETWEEN ? AND ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, writerId);
			pstmt.setInt(2, (qpageNo-1) * size + 1);
			pstmt.setInt(3, qpageNo * size);
			
			rs = pstmt.executeQuery();
			List<Quote> result = new ArrayList<Quote>();
			while (rs.next()) {
				result.add(convertQuote(rs));
			}
			return result;
		} finally {
			JdbcUtil.close(rs, pstmt);
		}
	}

	private Quote convertQuote(ResultSet rs) throws SQLException {
		return new Quote(
				rs.getInt("quote_no"), 
				rs.getString("quote_title"), 
				rs.getString("provider_id"), 
				new RequestSummary(
						rs.getInt("req_no"),
						rs.getString("req_writer"),
						rs.getString("req_title")),
				rs.getInt("price"), 
				rs.getString("location"), 
				rs.getString("info"), 
				rs.getInt("complete"));
	}

	public int selectCountQuoFromProvider(Connection conn, String providerId) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		String sql = "SELECT COUNT(*) FROM quote WHERE provider_id=?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, providerId);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				return rs.getInt(1);
			}
			return 0;
		} finally {
			JdbcUtil.close(rs, pstmt);
		}
	}

	public List<Quote> selectQuoFromProvider(Connection conn, int qpageNo, int size, String providerId) throws SQLException {
		String sql = "SELECT "
				+ "rn, "
				+ "quote_no, "
				+ "quote_title, "
				+ "provider_id, "
				+ "req_no, "
				+ "req_writer, "
				+ "req_title, "
				+ "price, "
				+ "location, "
				+ "info, "
				+ "complete "
				+ "FROM ("
				+ "	SELECT quote_no, "
				+ " 		quote_title, "
				+ "       provider_id, "
				+ "       req_no, "
				+ "       req_writer, "
				+ "			req_title, "
				+ "     	 price, "
				+ "     	  location, "
				+ "			info, "
				+ "			complete,  "
				+ "        ROW_NUMBER() "
				+ "          OVER ( "
				+ "            ORDER BY "
				+ "            quote_no "
				+ "            DESC) "
				+ "        rn "
				+ "  FROM quote WHERE provider_id=? "
				+ ") WHERE rn  BETWEEN ? AND ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, providerId);
			pstmt.setInt(2, (qpageNo-1) * size + 1);
			pstmt.setInt(3, qpageNo * size);
			
			rs = pstmt.executeQuery();
			List<Quote> result = new ArrayList<Quote>();
			while (rs.next()) {
				result.add(convertQuote(rs));
			}
			return result;
		} finally {
			JdbcUtil.close(rs, pstmt);
		}
	}

}
