package mypage.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import jdbc.ConnectionProvider;
import request.dao.RequestDao;
import request.service.Request;
import request.service.RequestPage;

public class ListRequestOfCustomerService {
	private RequestDao requestDao = new RequestDao();
	private int size = 5;
	public RequestPage getRequestPageOfCustomer(int pageNo, String writerId) {
		try (Connection conn = ConnectionProvider.getConnection()) {
			int totalReqOfCustomer = requestDao.selectCountReqOfCustomer(conn, writerId);
			List<Request> reqList = requestDao.selectOfCustomer(conn, pageNo, size, writerId);
			return new RequestPage(totalReqOfCustomer, pageNo, size, reqList);
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
}
