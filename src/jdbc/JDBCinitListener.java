package jdbc;

import java.sql.Connection;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Application Lifecycle Listener implementation class JDBCinitListener
 *
 */
@WebListener
public class JDBCinitListener implements ServletContextListener {

	/**
	 * Default constructor.
	 */
	public JDBCinitListener() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see ServletContextListener#contextDestroyed(ServletContextEvent)
	 */
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
	}

	/**
	 * @see ServletContextListener#contextInitialized(ServletContextEvent)
	 */
	public void contextInitialized(ServletContextEvent sce) {
		ServletContext application = sce.getServletContext();
		String url = application.getInitParameter("jdbcUrl");
		String user = application.getInitParameter("jdbcUser");
		String password = application.getInitParameter("jdbcPassword");

		// 1. 클래스 로딩
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		ConnectionProvider.setUrl(url);
		ConnectionProvider.setUser(user);
		ConnectionProvider.setPassword(password);

		// 2. drivermanager에서 connection
		// 3. close();

		Connection con = ConnectionProvider.getConnection();

		System.out.println("연결잘됨");

		// context root 경로
		String contextPath = application.getContextPath();
		application.setAttribute("root", contextPath);
	}

}
