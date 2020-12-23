package request.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.ConnectionProvider;
import jdbc.JdbcUtil;
import request.dao.RequestDao;

public class ReadRequestService {
	private RequestDao requestDao = new RequestDao();

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

}
