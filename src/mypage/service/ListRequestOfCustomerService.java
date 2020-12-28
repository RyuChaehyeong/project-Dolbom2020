package mypage.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import jdbc.ConnectionProvider;
import jdbc.JdbcUtil;
import request.dao.RequestDao;
import request.service.Request;
import request.service.RequestPage;

public class ListRequestOfCustomerService {
	private RequestDao requestDao = new RequestDao();
	private int size = 5;
	public RequestPage getRequestPageOfCustomer(int pageNo, String writerId) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			int totalReqOfCustomer = requestDao.selectCountReqOfCustomer(conn, writerId);
			List<Request> reqList = requestDao.selectOfCustomer(conn, pageNo, size, writerId);
			
			conn.commit();
			
			return new RequestPage(totalReqOfCustomer, pageNo, size, reqList);
			
		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException();
		} finally {
			JdbcUtil.close(conn);
		}
	}
}
