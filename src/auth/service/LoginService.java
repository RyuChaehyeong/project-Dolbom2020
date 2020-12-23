package auth.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.ConnectionProvider;
import jdbc.JdbcUtil;
import member.dao.MemberDao;
import member.model.Member;

public class LoginService {
	MemberDao memberDao = new MemberDao();
	
	public User join(String member_id, String password) {
		try (Connection conn = ConnectionProvider.getConnection()) {
			Member member = memberDao.selectById(conn, member_id);
			if (member == null) {
				throw new LoginFailException();
			}
			
			if (!member.matchPassword(password)) {
				throw new LoginFailException();
			}
			return new User(member.getMember_id(), member.getName(),member.getStatus());
		} catch (SQLException e) {
			throw new RuntimeException();
		} 
		
	}

}
