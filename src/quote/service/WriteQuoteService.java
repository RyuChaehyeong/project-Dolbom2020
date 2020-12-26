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
	

	public int write(WriteQuote writeQuo) throws SQLException {
		Connection conn = null;
		
		try {
			conn = ConnectionProvider.getConnection();
			
			Quote quote = toQuote(writeQuo);
			Quote savedQuote = quoteDao.insert(conn, quote);
			
			if (savedQuote == null) {
				throw new RuntimeException("fail to insert quote");
			}
			
			requestDao.updateQuoteCnt(conn, writeQuo.getReqSum().getReqNo());
			
			return savedQuote.getQuoteNo();
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
