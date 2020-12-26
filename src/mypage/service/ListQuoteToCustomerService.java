package mypage.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import jdbc.ConnectionProvider;
import quote.dao.QuoteDao;
import quote.service.Quote;

public class ListQuoteToCustomerService {
	private QuoteDao quoteDao = new QuoteDao();
	private int size = 5;
	
	public QuotePage getQuoteToCustomer(int qpageNo, String writerId) {
		try (Connection conn = ConnectionProvider.getConnection()) {
			int totalQuoToCustomer = quoteDao.selectCountQuoToCustomer(conn, writerId);
			List<Quote> quoList = quoteDao.selectQuoToCustomer(conn, qpageNo, size, writerId);
			return new QuotePage(totalQuoToCustomer, qpageNo, size, quoList);
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
	
	
	public QuotePage getQuoteFromProvider(int qpageNo, String providerId) {
		try (Connection conn = ConnectionProvider.getConnection()) {
			int totalQuoToCustomer = quoteDao.selectCountQuoFromProvider(conn, providerId);
			List<Quote> quoList = quoteDao.selectQuoFromProvider(conn, qpageNo, size, providerId);
			return new QuotePage(totalQuoToCustomer, qpageNo, size, quoList);
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
	

}
