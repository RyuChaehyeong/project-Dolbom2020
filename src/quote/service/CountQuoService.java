package quote.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.ConnectionProvider;
import quote.dao.QuoteDao;

public class CountQuoService {
	private QuoteDao quoteDao = new QuoteDao();
	
	//일반 회원에게 새로 도착한 견적서 개수
	public int countquo(String member_id) throws SQLException {
		try (Connection conn = ConnectionProvider.getConnection()) {
			int quo = quoteDao.selectCountById(conn, member_id);
			return quo;
		}
	}
	
	//돌봄이의 대기중인 견적서 개수
	public int countStandbyQuo(String member_id) throws SQLException {
		try (Connection conn = ConnectionProvider.getConnection()) {
			int quo = quoteDao.selectCountByPId(conn, member_id);
			return quo;
		}
	}

}
