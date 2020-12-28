package quote.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.ConnectionProvider;
import jdbc.JdbcUtil;
import quote.dao.QuoteDao;
import request.dao.RequestDao;

public class QuoteCompleteService {
	RequestDao requestDao = new RequestDao();
	QuoteDao quoteDao = new QuoteDao();
	
	public void completeContract(int quoteNo, int reqNo) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			quoteDao.updateComplete(conn, quoteNo);
			quoteDao.updateDenied(conn, quoteNo, reqNo);
			requestDao.updateComplete(conn, reqNo);
			
			conn.commit();
			
		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException();
			
		} finally {
			JdbcUtil.close(conn);
		}
	}

	
	
	
}
