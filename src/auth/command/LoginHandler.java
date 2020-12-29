package auth.command;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import auth.service.LoginFailException;
import auth.service.LoginService;
import auth.service.User;
import member.model.Member;
import member.service.ReadInfoService;
import mvc.command.CommandHandler;
import quote.service.CountQuoService;

public class LoginHandler implements CommandHandler{
	private static final String FORM_VIEW = "loginForm";
	LoginService loginService = new LoginService();
	ReadInfoService readInfoService = new ReadInfoService();
	CountQuoService countQuoService = new CountQuoService();

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		if (req.getMethod().equalsIgnoreCase("GET")) {
			return processForm(req, res);
		} else if (req.getMethod().equalsIgnoreCase("POST")) {
			return processSubmit(req, res);
		} else {
			res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}

	private String processForm(HttpServletRequest req, HttpServletResponse res) {
		return FORM_VIEW;
	}

	private String processSubmit(HttpServletRequest req, HttpServletResponse res) throws IOException, SQLException {
		String member_id = req.getParameter("id");
		String password = req.getParameter("password");
	
		Map<String, Boolean> errors = new HashMap<String, Boolean>();
		req.setAttribute("errors", errors);
		
		if (member_id == null || member_id.isEmpty()) {
			errors.put("id", Boolean.TRUE);
		}
		
		if (password == null || password.isEmpty()) {
			errors.put("password", Boolean.TRUE);
		}
		
		if (!errors.isEmpty()) {
			return FORM_VIEW;
		}
		
		try {
			User user = loginService.join(member_id, password);
			req.getSession().setAttribute("authUser", user);
			
			Member memberInfo = readInfoService.read(member_id);
			req.getSession().setAttribute("memberInfo", memberInfo);
			
			int newQuoNum = 0;
			
			if (user.getStatus() == 0 ) {
				//일반회원은 새로 도착한 견적서
				newQuoNum = countQuoService.countquo(member_id);
				req.getSession().setAttribute("newQuoNum", newQuoNum);				
		
			} else if (user.getStatus() == 1) {	
				//돌봄이 회원은 대기 중인 견적서
				newQuoNum = countQuoService.countStandbyQuo(member_id);
				req.getSession().setAttribute("newQuoNum", newQuoNum);
			}
			
			res.sendRedirect(req.getContextPath()+"/index.jsp");
			return null;
		} catch (LoginFailException e) {
			errors.put("idOrPwNotMatch", Boolean.TRUE);
			return FORM_VIEW;
		}
				
	}

}
