package member.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.ConnectionProvider;
import jdbc.JdbcUtil;
import member.dao.MemberDao;
import member.model.Member;

public class JoinService {
	MemberDao memberDao = new MemberDao();

	public void join(JoinRequest joinReq) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);

			Member m = memberDao.selectById(conn, joinReq.getMember_id());
			if (m != null) {
				JdbcUtil.rollback(conn);
				throw new DuplicateIdException();
			}

			Member member = new Member(joinReq.getMember_id(), joinReq.getName(), joinReq.getPassword(),
					joinReq.getAnimal(), joinReq.getStatus(), 0, joinReq.getEmail(), joinReq.getPhone(), 
					joinReq.getPostcode(), joinReq.getRoadAddress(), joinReq.getDetailAddress());

			memberDao.insert(conn, member);

		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(conn);
		}

	}

}
