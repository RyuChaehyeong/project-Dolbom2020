package member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jdbc.JdbcUtil;
import member.model.Member;

public class MemberDao {

	public Member selectById(Connection conn, String member_id) {
		String sql = "SELECT member_id, name, password, address, animal, status "
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
				member = new Member(rs.getString(1),
						rs.getString(2),
						rs.getString(3),
						rs.getString(4), 
						rs.getString(5), 
						rs.getString(6));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(pstmt);
		}
		return member;
	}

	public void insert(Connection conn, Member member) throws SQLException {
		String sql = "INSERT INTO dolbom_member (member_id, name, password, address, animal, status) "
				+ "VALUES (?, ?, ?, ?, ?, ?)";
		PreparedStatement pstmt = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getMember_id());
			pstmt.setString(2, member.getName());
			pstmt.setString(3, member.getPassword());
			pstmt.setString(4, member.getAddress());
			pstmt.setString(5, member.getAnimal());
			pstmt.setString(6, member.getStatus());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			JdbcUtil.close(pstmt);
		}
		
	}

}
