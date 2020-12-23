package request.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import request.service.ReadRequestService;
import request.service.Request;
import request.service.RequestNotFoundException;

public class ReadRequestHandler implements CommandHandler{
		ReadRequestService readService = new ReadRequestService();
	

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		String noVal = req.getParameter("no");
		int reqNum = Integer.parseInt(noVal);
		try {
			Request request = readService.getRequest(reqNum);
			req.setAttribute("request", request);
			return "readRequest";
		} catch (RequestNotFoundException e ) {
			req.getServletContext().log("no request", e);
			res.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}
	}

}
