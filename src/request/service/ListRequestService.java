package request.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import jdbc.ConnectionProvider;
import jdbc.JdbcUtil;
import request.dao.RequestDao;

public class ListRequestService {
	private RequestDao requestDao = new RequestDao();
	private int size = 15;
	public RequestPage getRequestPage(int pageNo) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			int totalReq = requestDao.selectCount(conn);
			List<Request> reqList = requestDao.select(conn, pageNo, size);
			
			conn.commit();
			return new RequestPage(totalReq, pageNo, size, reqList);
					
		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException();
		} finally {
			JdbcUtil.close(conn);
		}
	}
	
	
}
