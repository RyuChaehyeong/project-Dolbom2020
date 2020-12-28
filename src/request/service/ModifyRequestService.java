package request.service;

import java.sql.Connection;

import jdbc.ConnectionProvider;
import jdbc.JdbcUtil;
import request.dao.RequestDao;

public class ModifyRequestService {
	private RequestDao requestDao = new RequestDao();
	
	public void modify(ModifyRequest modReq) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			
			Request request = requestDao.selectById(conn, modReq.getReqNo());
			
			if (request == null) {
				throw new RequestNotFoundException();
			}
			
			if (!canModify(modReq.getWriter_id(), request)) {
				throw new PermissionDeniedException();
			}
			
			requestDao.update(conn, modReq);
			
			conn.commit();
		} catch (Exception e) {
			JdbcUtil.rollback(conn);
		} finally {
			JdbcUtil.close(conn);
		}
		
	}

	private boolean canModify(String writer_id, Request request) {
		return request.getWriter_id().equals(writer_id);
	}

}
