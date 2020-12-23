package request.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import jdbc.JdbcUtil;
import request.service.ModifyRequest;
import request.service.Request;
import request.service.SearchRequest;

public class RequestDao {

	public Request insert(Connection conn, Request request) throws SQLException {
		String sql = "INSERT INTO request (title, start_date, end_date, location, animal, writer_id, info ) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement(sql, new String[] {"req_no"});
			pstmt.setString(1, request.getTitle());
			pstmt.setDate(2, request.getStartDate());
			pstmt.setDate(3, request.getEndDate());
			pstmt.setString(4, request.getLocal());
			pstmt.setString(5, request.getAnimal());
			pstmt.setString(6, request.getWriter_id());
			pstmt.setString(7,  request.getInfo());
			
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
						request.getLocal(), 
						request.getInfo());
				
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
				+ "location, "
				+ "animal, "
				+ "writer_id, "
				+ "info "
				+ "FROM ("
				+ "	SELECT req_no, "
				+ " 		   title, "
				+ "       start_date, "
				+ "        end_date, "
				+ "        location, "
				+ "      	animal, "
				+ "        writer_id, "
				+ "			info, "
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
				rs.getString("location"),
				rs.getString("info"));
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
				+ "location=?, "
				+ "animal=?, "
				+ "writer_id=?, "
				+ "info=? "
				+ "WHERE req_no=?";
		
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, modReq.getTitle());
			pstmt.setDate(2, modReq.getStartDate());
			pstmt.setDate(3, modReq.getEndDate());
			pstmt.setString(4, modReq.getLocal());
			pstmt.setString(5, modReq.getAnimal());
			pstmt.setString(6, modReq.getWriter_id());
			pstmt.setString(7, modReq.getInfo());
			pstmt.setInt(8, modReq.getReqNo());
			
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
				+ "location, "
				+ "animal, "
				+ "writer_id, "
				+ "info "
				+ "FROM ("
				+ "	SELECT req_no, "
				+ " 		   title, "
				+ "       start_date, "
				+ "        end_date, "
				+ "        location, "
				+ "      	animal, "
				+ "        writer_id, "
				+ "			info, "
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
				+ "location, "
				+ "animal, "
				+ "writer_id, "
				+ "info "
				+ "FROM ("
				+ "	SELECT req_no, "
				+ " 		   title, "
				+ "       start_date, "
				+ "        end_date, "
				+ "        location, "
				+ "      	animal, "
				+ "        writer_id, "
				+ "			info, "
				+ "        ROW_NUMBER() "
				+ "          OVER ( "
				+ "            ORDER BY "
				+ "            req_no "
				+ "            DESC) "
				+ "        rn "
				+ "  FROM request "
				+ "WHERE location LIKE ? "
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
		
		String sql = "SELECT COUNT(*) FROM request WHERE location LIKE ?";
		
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
	


}
