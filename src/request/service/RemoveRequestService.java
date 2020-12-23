package request.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.ConnectionProvider;
import request.dao.RequestDao;

public class RemoveRequestService {
	private RequestDao requestDao = new RequestDao();
	public void remove (int reqNo) {
		try(Connection conn = ConnectionProvider.getConnection()) {
			requestDao.delete(conn, reqNo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
