package request.service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;

import jdbc.ConnectionProvider;
import jdbc.JdbcUtil;
import request.dao.RequestDao;

public class WriteRequestService {
	private RequestDao requestDao = new RequestDao();

	public int write(WriterRequest writeReq) throws SQLException {
		Connection conn = null;
		
		try {
			conn = ConnectionProvider.getConnection();
			
			Request request = toRequest(writeReq);
			Request savedRequest = requestDao.insert(conn, request);
			
			if (savedRequest == null) {
				throw new RuntimeException("fail to insert request");
			}
			return savedRequest.getReqNo();	
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		} finally {
			JdbcUtil.close(conn);
		}
	}

	private Request toRequest(WriterRequest writeReq) {
		Date sqlStart = new Date(writeReq.getStartDate().getTime());
		Date sqlEnd = new Date(writeReq.getEndDate().getTime());
		
		return new Request(null, writeReq.getWriter().getId(), writeReq.getTitle(), writeReq.getAnimal(),
				sqlStart, sqlEnd,
				writeReq.getLocal(), writeReq.getInfo());
	}

}
