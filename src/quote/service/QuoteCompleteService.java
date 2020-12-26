package quote.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.ConnectionProvider;
import quote.dao.QuoteDao;
import request.dao.RequestDao;

public class QuoteCompleteService {
	RequestDao requestDao = new RequestDao();
	QuoteDao quoteDao = new QuoteDao();
	
	public void completeContract(int quoteNo, int reqNo) throws SQLException {
		try (Connection conn = ConnectionProvider.getConnection()) {
			quoteDao.updateComplete(conn, quoteNo);
			quoteDao.updateDenied(conn, quoteNo, reqNo);
			requestDao.updateComplete(conn, reqNo);
			
		}
		
	}

	
	
	
}
