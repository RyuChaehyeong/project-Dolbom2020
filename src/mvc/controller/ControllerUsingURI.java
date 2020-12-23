package mvc.controller;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import mvc.command.NullHandler;

/**
 * Servlet implementation class ControllerUsingURI
 */

public class ControllerUsingURI extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String prefix = "/WEB-INF/view/";
	private String suffix = ".jsp";
	private Map<String, CommandHandler> map;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ControllerUsingURI() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void init() throws ServletException {
		map = new HashMap<>();
		ServletConfig config = getServletConfig();
		String configFilePath = config.getInitParameter("configFile").trim();
		
		ServletContext application = getServletContext();
		String filePath = application.getRealPath(configFilePath);

		try (FileReader fr = new FileReader(filePath);) {
			Properties properties = new Properties();
			properties.load(fr);

			Set<Object> keys = properties.keySet(); //properties.keySet()이 Set<Object>를 리턴
			for (Object key : keys) {
				Object value = properties.get(key); //properties.get이 Object를 리턴 
				String className = (String) value;

				try {
					Class c = Class.forName(className);
					Object o = c.newInstance();
					CommandHandler handler = (CommandHandler) o;
					map.put((String) key, handler);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		process(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		process(request, response);
	}

	private void process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// get방식이든 post방식이든 process method 실행
		String uri = request.getRequestURI();
		String root = request.getContextPath();

		String command = "";
		if (uri.startsWith(root)) {
			command = uri.substring(root.length());
		}

		CommandHandler handler = map.get(command);
		if (handler == null) {
			handler = new NullHandler();
		}


		String view = null;
		try {
			view = handler.process(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if (view != null) {
			request.getRequestDispatcher(prefix + view + suffix).forward(request, response);			
		}
	}
}
