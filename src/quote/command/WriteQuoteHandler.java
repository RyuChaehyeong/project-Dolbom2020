package quote.command;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import auth.service.User;
import mvc.command.CommandHandler;
import quote.service.WriteQuote;
import quote.service.WriteQuoteService;

public class WriteQuoteHandler implements CommandHandler {
	private static final String FORM_VIEW = "newQuoteForm";
	private WriteQuoteService writeService = new WriteQuoteService();

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		if (req.getMethod().equalsIgnoreCase("get")) {
			return processForm(req, res);
		} else if (req.getMethod().equalsIgnoreCase("post")) {
			return processSubmit(req, res);
		} else {
			res.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}

	private String processForm(HttpServletRequest req, HttpServletResponse res) throws IOException {
		return FORM_VIEW;
	}

	private String processSubmit(HttpServletRequest req, HttpServletResponse res) throws IOException, SQLException {
		Map<String, Boolean> errors = new HashMap<String, Boolean>();
		req.setAttribute("errors", errors);
		String reqNoVal = req.getParameter("reqNo");
		Integer reqNo = Integer.parseInt(reqNoVal);
		
		
		User user = (User) req.getSession().getAttribute("authUser");
		
		
		WriteQuote writeQuo = createQuote(user, req, reqNo);
		
		writeQuo.validate(errors);
		
		if (!errors.isEmpty()) {
			return FORM_VIEW;
		}
		
		int newQuoteNo = writeService.write(writeQuo);
		req.setAttribute("newQuoteNo", newQuoteNo);
		res.sendRedirect(req.getContextPath()+"/request/list.do");
		
		return null;	
				
				
	}

	private WriteQuote createQuote(User user, HttpServletRequest req, Integer reqNo) {
		String provider = user.getMember_id();
	
		String reqWriter = req.getParameter("reqWriter");
		String title = req.getParameter("title");
		String location = req.getParameter("loc");
		String price = req.getParameter("price");
		String info = req.getParameter("info");
		return new WriteQuote(title,
				provider,
				reqNo,
				reqWriter,
				Integer.parseInt(price),
				location,
				info);
	}
	
}
