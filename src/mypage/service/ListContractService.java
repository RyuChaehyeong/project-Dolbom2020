package mypage.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import contract.dao.ContractDao;
import contract.model.Contract;
import jdbc.ConnectionProvider;
import jdbc.JdbcUtil;

public class ListContractService {
	private ContractDao contractDao = new ContractDao();
	private int size = 5;
	
	public ContractPage getContractCustomer(int cpageNo, String userId) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			int totalContract = contractDao.selectCountConCustomer(conn, userId);
			List<Contract> contractList = contractDao.selectConCustomer(conn, cpageNo, size, userId);
			
			conn.commit();
			
			return new ContractPage(totalContract, cpageNo, size, contractList);
		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException();
		} finally {
			JdbcUtil.close(conn);
		}
	}

	
	
	public ContractPage getContractProvider(int cpageNo, String userId) {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false);
			int totalContract = contractDao.selectCountConProvider(conn, userId);
			List<Contract> contractList = contractDao.selectConProvider(conn, cpageNo, size, userId);
			return new ContractPage(totalContract, cpageNo, size, contractList);

		} catch (SQLException e) {
			JdbcUtil.rollback(conn);
			throw new RuntimeException();
		} finally {
			JdbcUtil.close(conn);
		}
	}
}
