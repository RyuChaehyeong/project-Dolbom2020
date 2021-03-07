package request.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import jdbc.ConnectionProvider;
import jdbc.JdbcUtil;
import request.service.ModifyRequest;
import request.service.Request;
import request.service.SearchRequest;

public class RequestDao {

	public Request insert(Connection conn, Request request) throws SQLException {
		String sql = "INSERT INTO request (title, start_date, end_date, postcode, roadaddress, animal, writer_id, info, quote_cnt, complete ) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, 0, 0)";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement(sql, new String[] {"req_no"});
			int col = 1;
			pstmt.setString(col++, request.getTitle());
			pstmt.setDate(col++, request.getStartDate());
			pstmt.setDate(col++, request.getEndDate());
			pstmt.setString(col++, request.getPostcode());
			pstmt.setString(col++, request.getRoadAddress());
			pstmt.setString(col++, request.getAnimal());
			pstmt.setString(col++, request.getWriter_id());
			pstmt.setString(col++,  request.getInfo());
			
			int cnt = pstmt.executeUpdate();
			
			if (cnt == 1) {
				rs = pstmt.getGeneratedKeys();
				int key = 0;	
				if (rs.next()) {
					key = rs.getInt(1);			
				}
				return new Request(key, 
						request.getWriter_id(),
						request.getTitle(), 
						request.getAnimal(), 
						request.getStartDate(), 
						request.getEndDate(), 
						request.getPostcode(),
						request.getRoadAddress(),
						request.getInfo(),
						0,
						0);
				
			} else {
				return null;
			}
			
		} finally {
			JdbcUtil.close(rs, pstmt);
		}
	}

	public int selectCount(Connection conn) throws SQLException {
		String sql = "SELECT COUNT(*) FROM request";
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			if (rs.next()) {
				return rs.getInt(1);
			}
			return 0;
		} finally {
			JdbcUtil.close(rs, stmt);
		}
	}

	public List<Request> select(Connection conn, int pageNo, int size) throws SQLException {
		String sql = "SELECT "
				+ "rn, "
				+ "req_no, "
				+ "title, "
				+ "start_date, "
				+ "end_date, "
				+ "postcode,"
				+ "roadaddress, "
				+ "animal, "
				+ "writer_id, "
				+ "info, "
				+ "quote_cnt,"
				+ "complete "
				+ "FROM ("
				+ "	SELECT req_no, "
				+ " 		   title, "
				+ "       start_date, "
				+ "        end_date, "
				+ "        postcode,"
				+ " 		roadaddress,"
				+ "      	animal, "
				+ "        writer_id, "
				+ "			info, "
				+ "			quote_cnt ,"
				+ "			complete,  "
				+ "        ROW_NUMBER() "
				+ "          OVER ( "
				+ "            ORDER BY "
				+ "            req_no "
				+ "            DESC) "
				+ "        rn "
				+ "  FROM request "
				+ ") WHERE rn  BETWEEN ? AND ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, (pageNo-1) * size + 1);
			pstmt.setInt(2, pageNo * size);
			
			rs = pstmt.executeQuery();
			List<Request> result = new ArrayList<Request>();
			while (rs.next()) {
				result.add(convertRequest(rs));
			}
			return result;
		} finally {
			JdbcUtil.close(rs, pstmt);
		}
	}

	private Request convertRequest(ResultSet rs) throws SQLException {
		return new Request(rs.getInt("req_no"),
				rs.getString("writer_id"),
				rs.getString("title"),
				rs.getString("animal"),
				rs.getDate("start_date"),
				rs.getDate("end_date"),
				rs.getString("postcode"),
				rs.getString("roadaddress"),
				rs.getString("info"),
				rs.getInt("quote_cnt"),
				rs.getInt("complete"));
	}

	public Request selectById(Connection conn, int reqNum) throws SQLException {
		String sql = "SELECT * "
				+ "FROM request "
				+ "WHERE req_no=?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		Request request = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, reqNum);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				request = convertRequest(rs);
			}
			return request;
			
		} finally {
			JdbcUtil.close(rs, pstmt);
		}
	}

	public void update(Connection conn, ModifyRequest modReq) throws SQLException {
		String sql = "UPDATE request "
				+ "SET title=?, "
				+ "start_date=?, "
				+ "end_date=?, "
				+ "postcode=?,"
				+ "roadaddress=?, "
				+ "animal=?, "
				+ "writer_id=?, "
				+ "info=? "
				+ "WHERE req_no=?";
		
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			int col = 1;
			pstmt.setString(col++, modReq.getTitle());
			pstmt.setDate(col++, modReq.getStartDate());
			pstmt.setDate(col++, modReq.getEndDate());
			pstmt.setString(col++, modReq.getPostcode());
			pstmt.setString(col++, modReq.getRoadaddress());
			pstmt.setString(col++, modReq.getAnimal());
			pstmt.setString(col++, modReq.getWriter_id());
			pstmt.setString(col++, modReq.getInfo());
			pstmt.setInt(col++, modReq.getReqNo());
			
			pstmt.executeUpdate();
		}
		
	}

	public void delete(Connection conn, int reqNo) throws SQLException {
		String sql = "DELETE FROM request "
				+ "WHERE req_no=?";
		
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, reqNo);
			pstmt.executeUpdate();
		}
		
	}

	public List<Request> selectSearchAni(Connection conn, int pageNo, int size, SearchRequest searchReq) throws SQLException {
		String sql = "SELECT "
				+ "rn, "
				+ "req_no, "
				+ "title, "
				+ "start_date, "
				+ "end_date, "
				+ "postcode,"
				+ "roadaddress, "
				+ "animal, "
				+ "writer_id, "
				+ "info, "
				+ "quote_cnt,"
				+ "complete "
				+ "FROM ("
				+ "	SELECT req_no, "
				+ " 		   title, "
				+ "       start_date, "
				+ "        end_date, "
				+ "        postcode, "
				+ "			roadaddress, "
				+ "      	animal, "
				+ "        writer_id, "
				+ "			info, "
				+ "			quote_cnt,"
				+ "			complete, "
				+ "        ROW_NUMBER() "
				+ "          OVER ( "
				+ "            ORDER BY "
				+ "            req_no "
				+ "            DESC) "
				+ "        rn "
				+ "  FROM request "
				+ "WHERE animal LIKE ? "
				+ ") WHERE rn  BETWEEN ? AND ? ";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+searchReq.getWord()+"%");
			pstmt.setInt(2, (pageNo-1) * size + 1);
			pstmt.setInt(3, pageNo * size);
			
			rs = pstmt.executeQuery();
			List<Request> result = new ArrayList<Request>();
			while (rs.next()) {
				result.add(convertRequest(rs));
			}
			return result;
		} finally {
			JdbcUtil.close(rs, pstmt);
		}
	}

	public int selectCountSearchAni(Connection conn, SearchRequest searchReq) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT COUNT(*) FROM request WHERE animal LIKE ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+searchReq.getWord()+"%");
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				return rs.getInt(1);
			}
			return 0;
		} finally {
			JdbcUtil.close(rs, pstmt);
		}
		
		
	}

	public List<Request> selectSearchLoc(Connection conn, int pageNo, int size, SearchRequest searchReq) throws SQLException {
		String sql = "SELECT "
				+ "rn, "
				+ "req_no, "
				+ "title, "
				+ "start_date, "
				+ "end_date, "
				+ "postcode,"
				+ "roadaddress, "
				+ "animal, "
				+ "writer_id, "
				+ "info, "
				+ "quote_cnt,"
				+ "complete "
				+ "FROM ("
				+ "	SELECT req_no, "
				+ " 		   title, "
				+ "       start_date, "
				+ "        end_date, "
				+ "        postcode,"
				+ "			roadaddress, "
				+ "      	animal, "
				+ "        writer_id, "
				+ "			info, "
				+ "			quote_cnt, "
				+ "			complete, "
				+ "        ROW_NUMBER() "
				+ "          OVER ( "
				+ "            ORDER BY "
				+ "            req_no "
				+ "            DESC) "
				+ "        rn "
				+ "  FROM request "
				+ "WHERE roadaddress LIKE ? "
				+ ") WHERE rn  BETWEEN ? AND ? ";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+searchReq.getWord()+"%");
			pstmt.setInt(2, (pageNo-1) * size + 1);
			pstmt.setInt(3, pageNo * size);
			
			rs = pstmt.executeQuery();
			List<Request> result = new ArrayList<Request>();
			while (rs.next()) {
				result.add(convertRequest(rs));
			}
			return result;
		} finally {
			JdbcUtil.close(rs, pstmt);
		}
	}

	public int selectCountSearchLoc(Connection conn, SearchRequest searchReq) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT COUNT(*) FROM request WHERE roadaddress LIKE ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+searchReq.getWord()+"%");
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				return rs.getInt(1);
			}
			return 0;
		} finally {
			JdbcUtil.close(rs, pstmt);
		}
	}

	public void updateQuoteCnt(Connection conn, Integer reqNo) throws SQLException {
		String sql = "UPDATE request "
				+ "SET quote_cnt = quote_cnt+1 "
				+ "WHERE req_no=?";
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, reqNo);
			pstmt.executeUpdate();
		}
		
	}

	public int selectCountReqOfCustomer(Connection conn, String writerId) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		String sql = "SELECT COUNT(*) FROM request WHERE writer_id=?";
		
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

	public List<Request> selectOfCustomer(Connection conn, int pageNo, int size, String writerId) throws SQLException {
		String sql = "SELECT "
				+ "rn, "
				+ "req_no, "
				+ "title, "
				+ "start_date, "
				+ "end_date, "
				+ "postcode,"
				+ "roadaddress, "
				+ "animal, "
				+ "writer_id, "
				+ "info, "
				+ "quote_cnt,"
				+ "complete "
				+ "FROM ("
				+ "	SELECT req_no, "
				+ " 		   title, "
				+ "       start_date, "
				+ "        end_date, "
				+ "        postcode,"
				+ "			roadaddress, "
				+ "      	animal, "
				+ "        writer_id, "
				+ "			info, "
				+ "			quote_cnt ,"
				+ "			complete,  "
				+ "        ROW_NUMBER() "
				+ "          OVER ( "
				+ "            ORDER BY "
				+ "            req_no "
				+ "            DESC) "
				+ "        rn "
				+ "  FROM request WHERE writer_id=? "
				+ ") WHERE rn  BETWEEN ? AND ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, writerId);
			pstmt.setInt(2, (pageNo-1) * size + 1);
			pstmt.setInt(3, pageNo * size);
			
			rs = pstmt.executeQuery();
			List<Request> result = new ArrayList<Request>();
			while (rs.next()) {
				result.add(convertRequest(rs));
			}
			return result;
		} finally {
			JdbcUtil.close(rs, pstmt);
		}
	}

	public void updateComplete(Connection conn, int reqNo) throws SQLException {
		String sql = "UPDATE request SET complete=1 WHERE req_no=?";
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, reqNo);
			pstmt.executeUpdate();
		}
		
	}
	


}
