package quote.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jdbc.JdbcUtil;
import quote.service.Quote;
import request.service.Provider;

public class QuoteDao {

	public Quote insert(Connection conn, Quote quote) throws SQLException {
		String sql = "INSERT INTO quote (quote_title, provider_id, req_no, req_writer, price, location, info ) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, quote.getTitle());
			pstmt.setString(2, quote.getProvider());
			pstmt.setInt(3, quote.getReqNo());
			pstmt.setString(4, quote.getReqWriter());
			pstmt.setInt(5, quote.getPrice());
			pstmt.setString(6, quote.getLocation());
			pstmt.setString(7, quote.getInfo());
			
			int cnt = pstmt.executeUpdate();
			
			if (cnt == 1) {
				rs = pstmt.getGeneratedKeys();
				int key = 0;
				int complete = 0;
				
				if (rs.next()) {
					key = rs.getInt("quote_no");
					complete = rs.getInt("complete");
				}
				
				return new Quote(key,
						quote.getTitle(),
						quote.getProvider(), 
						quote.getReqNo(), 
						quote.getReqWriter(), 
						quote.getPrice(), 
						quote.getLocation(), 
						quote.getInfo());
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

}
