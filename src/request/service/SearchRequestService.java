package request.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import jdbc.ConnectionProvider;
import request.dao.RequestDao;

public class SearchRequestService {
	private RequestDao requestDao = new RequestDao();
	private int size = 15;
	public RequestPage search (int pageNo, SearchRequest searchReq) {
		try (Connection conn = ConnectionProvider.getConnection()) {
			List<Request> reqList = null;
			int totalSearchedReq = 0;
			if (searchReq.getField().equals("animal")) {
				reqList = requestDao.selectSearchAni(conn, pageNo, size, searchReq);			
				totalSearchedReq = requestDao.selectCountSearchAni(conn, searchReq);				
			} else if (searchReq.getField().equals("location")) {
				reqList = requestDao.selectSearchLoc(conn, pageNo, size, searchReq);			
				totalSearchedReq = requestDao.selectCountSearchLoc(conn, searchReq);								
			}
			
			return new RequestPage(totalSearchedReq, pageNo, size, reqList);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
}
