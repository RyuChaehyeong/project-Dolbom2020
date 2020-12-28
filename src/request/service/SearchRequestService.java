package request.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import jdbc.ConnectionProvider;
import jdbc.JdbcUtil;
import request.dao.RequestDao;

public class SearchRequestService {
	private RequestDao requestDao = new RequestDao();
	private int size = 15;
	public RequestPage search (int pageNo, SearchRequest searchReq) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			List<Request> reqList = null;
			int totalSearchedReq = 0;
			if (searchReq.getField().equals("animal")) {
				reqList = requestDao.selectSearchAni(conn, pageNo, size, searchReq);			
				totalSearchedReq = requestDao.selectCountSearchAni(conn, searchReq);				
			} else if (searchReq.getField().equals("location")) {
				reqList = requestDao.selectSearchLoc(conn, pageNo, size, searchReq);			
				totalSearchedReq = requestDao.selectCountSearchLoc(conn, searchReq);								
			}
			
			conn.commit();
			return new RequestPage(totalSearchedReq, pageNo, size, reqList);
			
		} catch (SQLException e) {
			e.printStackTrace();
			JdbcUtil.rollback(conn);
			return null;
			
		} finally {
			JdbcUtil.close(conn);
		}
	}
}
