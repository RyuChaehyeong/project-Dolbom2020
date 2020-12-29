package member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jdbc.JdbcUtil;
import member.model.Member;

public class MemberDao {

	public Member selectById(Connection conn, String member_id) {
		String sql = "SELECT member_id, name, password, address, animal, status, score, email, phone "
				+ "FROM dolbom_member "
				+ "WHERE member_id=?";
		Member member = null;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member_id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				int col = 1;
				member = new Member(
						rs.getString(col++),
						rs.getString(col++),
						rs.getString(col++),
						rs.getString(col++), 
						rs.getString(col++), 
						rs.getInt(col++),
						rs.getDouble(col++),
						rs.getString(col++),
						rs.getString(col++));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(pstmt);
		}
		return member;
	}

	public void insert(Connection conn, Member member) throws SQLException {
		String sql = "INSERT INTO dolbom_member (member_id, name, password, address, animal, status, score, email, phone ) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ? ,? ,?)";
		PreparedStatement pstmt = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			int col = 1;
			pstmt.setString(col++, member.getMember_id());
			pstmt.setString(col++, member.getName());
			pstmt.setString(col++, member.getPassword());
			pstmt.setString(col++, member.getAddress());
			pstmt.setString(col++, member.getAnimal());
			pstmt.setInt(col++, member.getStatus());
			pstmt.setDouble(col++, member.getScore());
			pstmt.setString(col++, member.getEmail());
			pstmt.setString(col++, member.getPhone());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			JdbcUtil.close(pstmt);
		}
		
	}

	public void updateScore(Connection conn, String target) throws SQLException {
		String sql = "UPDATE dolbom_member "
				+ "SET score=(SELECT ROUND(AVG(score),1) FROM review WHERE target=?) "
				+ "WHERE member_id=?";
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, target);
			pstmt.setString(2, target);
			pstmt.executeUpdate();
		}
	}

}
