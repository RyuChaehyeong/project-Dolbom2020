package quote.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import jdbc.ConnectionProvider;
import mypage.service.QuotePage;
import quote.dao.QuoteDao;

public class ReadQuoteService {
	private QuoteDao quoteDao = new QuoteDao();

	public Quote getQuote(int quoteNo) {
		try (Connection conn = ConnectionProvider.getConnection()) {
			Quote quote = quoteDao.selectByNo(conn, quoteNo);
			if (quote == null) {
				throw new QuoteNotFoundException();
			}
			return quote;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	public QuotePage getQuoteByreqNo(int qpageNo, int reqNo) {
		int size = 5;
		try (Connection conn = ConnectionProvider.getConnection()) {
			int totalQuoToCustomer = quoteDao.selectCountByreqNo(conn, reqNo);
			List<Quote> quoList = quoteDao.selectByreqNo(conn, qpageNo, size, reqNo);
			return new QuotePage(totalQuoToCustomer, qpageNo, size, quoList);
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

}
