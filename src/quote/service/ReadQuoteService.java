package quote.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.ConnectionProvider;
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

}
