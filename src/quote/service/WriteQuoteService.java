package quote.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.ConnectionProvider;
import jdbc.JdbcUtil;
import quote.dao.QuoteDao;
import request.dao.RequestDao;

public class WriteQuoteService {
	private QuoteDao quoteDao = new QuoteDao(); 
	private RequestDao requestDao = new RequestDao();
	

	public int write(WriteQuote writeQuo) {
		Connection conn = null;
		
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			Quote quote = toQuote(writeQuo);
			Quote savedQuote = quoteDao.insert(conn, quote);
			
			if (savedQuote == null) {
				throw new RuntimeException("fail to insert quote");
			}
			
			requestDao.updateQuoteCnt(conn, writeQuo.getReqSum().getReqNo());
			
			conn.commit();
			
			return savedQuote.getQuoteNo();
			
		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException();
			
		} finally {
			JdbcUtil.close(conn);
		}
	}

	private Quote toQuote(WriteQuote writeQuo) {
		return new Quote(
				null, 
				writeQuo.getTitle(),
				writeQuo.getProvider(),
				writeQuo.getReqSum(),
				writeQuo.getPrice(),
				writeQuo.getLocation(),
				writeQuo.getInfo(), 
				null);
	}
	
}
