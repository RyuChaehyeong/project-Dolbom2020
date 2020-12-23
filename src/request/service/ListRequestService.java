package request.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import jdbc.ConnectionProvider;
import request.dao.RequestDao;

public class ListRequestService {
	private RequestDao requestDao = new RequestDao();
	private int size = 15;
	public RequestPage getRequestPage(int pageNo) {
		try (Connection conn = ConnectionProvider.getConnection()) {
			int totalReq = requestDao.selectCount(conn);
			List<Request> reqList = requestDao.select(conn, pageNo, size);
			return new RequestPage(totalReq, pageNo, size, reqList);
					
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
	
	
}
