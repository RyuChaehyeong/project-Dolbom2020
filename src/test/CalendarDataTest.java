package test;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CalendarDataTest
 */
@WebServlet("/test03")
public class CalendarDataTest extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CalendarDataTest() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String start = request.getParameter("startDate");
		String end = request.getParameter("endDate");
		if (start.isEmpty()) {
			System.out.println("yes");
		}
		if (end.isEmpty()) {
			System.out.println("yes!");
		}
		
		String todayDate = todayDate();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		Date start_util = null;
		try {
			start_util = sdf.parse(start);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Date end_util = null; 
		try {
			end_util = sdf.parse(end);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		Date today_util = null; 
		try {
			today_util = sdf.parse(todayDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		System.out.println(start_util.after(today_util));
		System.out.println(end_util.after(start_util));
	}

	private String todayDate() {
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH) + 1;
		int date = cal.get(Calendar.DATE);
		return year + "-" + month + "-" + date;
	}

}
