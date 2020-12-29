package contract.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import contract.model.Contract;
import jdbc.JdbcUtil;
import quote.service.Quote;

public class ContractDao {

	public void insertContract(Connection conn, Quote quote) throws SQLException {
		String sql = "INSERT INTO contract (req_no, quo_no, customer_id, provider_id) "
				+ "VALUES (?, ?, ?, ?)";
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			int col = 1;
			pstmt.setInt(col++, quote.getReqSum().getReqNo());
			pstmt.setInt(col++, quote.getQuoteNo());
			pstmt.setString(col++, quote.getReqSum().getReqWriter());
			pstmt.setString(col++, quote.getProvider());
			
			pstmt.executeUpdate();
		}
		
	}

	public int selectCountConCustomer(Connection conn, String userId) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	 
		String sql = "SELECT COUNT(*) FROM contract WHERE customer_id=? ";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				return rs.getInt(1);
			}
			return 0;
		} finally {
			JdbcUtil.close(rs, pstmt);
		}
	}

	public List<Contract> selectConCustomer(Connection conn, int cpageNo, int size, String userId) throws SQLException {
		String sql = "SELECT "
				+ "rn, "
				+ "contract_no, "
				+ "req_no,  "
				+ "quo_no, "
				+ "customer_review, "
				+ "provider_review, "
				+ "customer_id,  "
				+ "provider_id "
				+ "FROM ("
				+ "	SELECT contract_no, "
				+ " 		req_no, "
				+ "      quo_no, "
				+ "       customer_review, "
				+ "       provider_review, "
				+ "       customer_id, "
				+ "		  provider_id, "
				+ "        ROW_NUMBER() "
				+ "          OVER ( "
				+ "            ORDER BY "
				+ "            contract_no "
				+ "            DESC) "
				+ "        rn "
				+ "  FROM contract WHERE customer_id=?"
				+ ") WHERE rn  BETWEEN ? AND ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			pstmt.setInt(2, (cpageNo-1) * size + 1);
			pstmt.setInt(3, cpageNo * size);
			
			rs = pstmt.executeQuery();
			List<Contract> result = new ArrayList<Contract>();
			while (rs.next()) {
				result.add(convertContract(rs));
			}
			return result;
			
			
		} finally {
			JdbcUtil.close(rs, pstmt);
		}
	}


	public int selectCountConProvider(Connection conn, String userId) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	 
		String sql = "SELECT COUNT(*) FROM contract WHERE provider_id=? ";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				return rs.getInt(1);
			}
			return 0;
		} finally {
			JdbcUtil.close(rs, pstmt);
		}
	}

	public List<Contract> selectConProvider(Connection conn, int cpageNo, int size, String userId) throws SQLException {
		String sql = "SELECT "
				+ "rn, "
				+ "contract_no, "
				+ "req_no,  "
				+ "quo_no, "
				+ "customer_review, "
				+ "provider_review, "
				+ "customer_id,  "
				+ "provider_id "
				+ "FROM ("
				+ "	SELECT contract_no, "
				+ " 		req_no, "
				+ "      quo_no, "
				+ "      customer_review, "
				+ "		provider_review, "
				+ "       customer_id, "
				+ "		  provider_id, "
				+ "        ROW_NUMBER() "
				+ "          OVER ( "
				+ "            ORDER BY "
				+ "            contract_no "
				+ "            DESC) "
				+ "        rn "
				+ "  FROM contract WHERE provider_id=?"
				+ ") WHERE rn  BETWEEN ? AND ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			pstmt.setInt(2, (cpageNo-1) * size + 1);
			pstmt.setInt(3, cpageNo * size);
			
			rs = pstmt.executeQuery();
			List<Contract> result = new ArrayList<Contract>();
			while (rs.next()) {
				result.add(convertContract(rs));
			}
			return result;
			
			
		} finally {
			JdbcUtil.close(rs, pstmt);
		}
	}

	private Contract convertContract(ResultSet rs) throws SQLException {
		return new Contract(
				rs.getInt("contract_no"),
				rs.getInt("req_no"),
				rs.getInt("quo_no"), 
				rs.getString("customer_id"), 
				rs.getString("provider_id"), 
				rs.getInt("customer_review"), 
				rs.getInt("provider_review"));		
	}

	public void updateCReview(Connection conn, Integer quoNum) throws SQLException {
		String sql = "UPDATE contract SET customer_review=1 WHERE quo_no=?";
		
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, quoNum);
			pstmt.executeUpdate();
		}
		
	}
	public void updatePReview(Connection conn, Integer quoNum) throws SQLException {
		String sql = "UPDATE contract SET provider_review=1 WHERE quo_no=?";
		
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, quoNum);
			pstmt.executeUpdate();
		}
		
	}

	public Contract selectByQuoNum(Connection conn, Integer quoNum) throws SQLException {
		String sql = "SELECT * from contract WHERE quo_no=?";
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Contract contract = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, quoNum);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				contract = convertContract(rs);
			}
			return contract;
		} finally {
			JdbcUtil.close(rs, pstmt);
		}
	}

}
