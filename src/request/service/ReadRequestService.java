package request.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import jdbc.ConnectionProvider;
import jdbc.JdbcUtil;
import quote.dao.QuoteDao;
import request.dao.RequestDao;

public class ReadRequestService {
	private RequestDao requestDao = new RequestDao();
	private QuoteDao quoteDao = new QuoteDao();

	public Request getRequest(int reqNum) {
		try (Connection conn = ConnectionProvider.getConnection()) {
			Request request = requestDao.selectById(conn, reqNum);
			if (request == null) {
				throw new RequestNotFoundException();
			}
			
			return request;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} 
	}

	public List<String> getProviders(int reqNum) throws SQLException {
		List<String> providerList = null;
		try (Connection conn = ConnectionProvider.getConnection()) {
			providerList = quoteDao.selectByReq_no(conn, reqNum);
		}
		return providerList;
	}

}
