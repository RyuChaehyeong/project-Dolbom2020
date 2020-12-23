package request.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import auth.service.User;
import mvc.command.CommandHandler;
import request.service.ReadRequestService;
import request.service.RemoveRequestService;
import request.service.Request;

public class RemoveRequestHandler implements CommandHandler {
	private ReadRequestService readService = new ReadRequestService();
	private RemoveRequestService removeService = new RemoveRequestService();


	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		User user = (User) req.getSession().getAttribute("authUser");
		
		String reqNo = req.getParameter("no");
		int no = Integer.parseInt(reqNo);
		
		Request request = readService.getRequest(no);
		if (!sameUserId(user, request)) {
			res.sendError(HttpServletResponse.SC_FORBIDDEN);
			return null;
		}
		
		try {
			removeService.remove(no);
			res.sendRedirect(req.getContextPath()+"/request/list.do");
		} catch (Exception e) {
			throw new RuntimeException();
		}
		return null;
	}


	private boolean sameUserId(User user, Request request) {
		return user.getMember_id().equals(request.getWriter_id());
	}

}
