package test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jdbc.ConnectionProvider;
import request.dao.RequestDao;
import request.model.Writer;
import request.service.Request;

/**
 * Servlet implementation class RequestInsertTestServlet
 */
@WebServlet("/testRequestInsert1")
public class RequestInsertTestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RequestInsertTestServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String stDate = "2020-12-22";
		String enDate = "2020-12-26";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date start = null;
		try {
			start = sdf.parse(stDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Date end = null;
		try {
			end = sdf.parse(enDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		java.sql.Date sqlStart = new java.sql.Date(start.getTime());
		java.sql.Date sqlEnd = new java.sql.Date(end.getTime());
		
		Request req = new Request(
				0, 
				"a", 
				"강냉이", 
				"멈머", 
				sqlStart, 
				sqlEnd, 
				"뉴옥", 
				"사나움");
		RequestDao dao = new RequestDao();
		
		try (Connection conn = ConnectionProvider.getConnection()) {
			Request rrr = dao.insert(conn, req);
			if (rrr != null) {
				System.out.println(rrr.getReqNo());				
			} else {
				System.out.println("입력실풰!!!!!!");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
