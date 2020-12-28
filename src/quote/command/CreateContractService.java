package quote.command;

import java.sql.Connection;
import java.sql.SQLException;

import contract.dao.ContractDao;
import jdbc.ConnectionProvider;
import quote.service.Quote;

public class CreateContractService {
	private ContractDao contractDao = new ContractDao();
	
	public void createContract( Quote quote) throws SQLException {
		try (Connection conn = ConnectionProvider.getConnection()) {
			contractDao.insertContract(conn, quote);			
		}
		
	}
	

}
