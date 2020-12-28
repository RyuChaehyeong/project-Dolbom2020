package mypage.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import mypage.service.QuotePage;
import quote.service.Quote;
import quote.service.ReadQuoteService;
import request.service.ReadRequestService;
import request.service.Request;
import request.service.RequestNotFoundException;

public class ReadListHandler implements CommandHandler {
	ReadRequestService readReqService = new ReadRequestService();
	ReadQuoteService readQuoService = new ReadQuoteService();

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		String reqNoVal = req.getParameter("reqNo");
		int reqNo = Integer.parseInt(reqNoVal);
		try {
			Request request = readReqService.getRequest(reqNo);
			req.setAttribute("request", request);
			
			
			String quotePageVal = req.getParameter("qpageNo");
			int qpageNo = 1;
			if (quotePageVal != null) {
				qpageNo = Integer.parseInt(quotePageVal);
			}
			
			QuotePage quotePage = readQuoService.getQuoteByreqNo(qpageNo, reqNo);
			req.setAttribute("quotePage", quotePage);
			return "readReqAndQuotes";
			
		} catch (RequestNotFoundException e) {
			req.getServletContext().log("no request", e);
			res.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}
		
		
		
	}

}
