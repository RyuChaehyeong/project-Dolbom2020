package member.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.ConnectionProvider;
import member.dao.MemberDao;
import member.model.Member;

public class ReadInfoService {
	MemberDao memberDao = new MemberDao();
	
	public Member read(String member_id) throws SQLException {
		try (Connection conn = ConnectionProvider.getConnection()) {
			Member member = memberDao.selectById(conn, member_id);
			if (member != null) {
				return member;
			} else {
				throw new MemberNotFoundException();
			}
		}
	}

}
