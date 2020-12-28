package mypage.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import jdbc.ConnectionProvider;
import jdbc.JdbcUtil;
import quote.dao.QuoteDao;
import quote.service.Quote;

public class ListQuoteToCustomerService {
	private QuoteDao quoteDao = new QuoteDao();
	private int size = 5;
	
	public QuotePage getQuoteToCustomer(int qpageNo, String writerId) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			int totalQuoToCustomer = quoteDao.selectCountQuoToCustomer(conn, writerId);
			List<Quote> quoList = quoteDao.selectQuoToCustomer(conn, qpageNo, size, writerId);
			
			conn.commit();
			
			return new QuotePage(totalQuoToCustomer, qpageNo, size, quoList);
			
		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException();
		} finally {
			JdbcUtil.close(conn);
		}
	}
	
	
	public QuotePage getQuoteFromProvider(int qpageNo, String providerId) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
		
			int totalQuoToCustomer = quoteDao.selectCountQuoFromProvider(conn, providerId);
			List<Quote> quoList = quoteDao.selectQuoFromProvider(conn, qpageNo, size, providerId);
			
			conn.commit();
			
			return new QuotePage(totalQuoToCustomer, qpageNo, size, quoList);
			
		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException();
		} finally {
			JdbcUtil.close(conn);
		}
	}
	

}
