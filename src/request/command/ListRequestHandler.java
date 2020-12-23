package request.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import request.service.ListRequestService;
import request.service.RequestPage;

public class ListRequestHandler implements CommandHandler {
	private ListRequestService listService = new ListRequestService();

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		String pageNoVal = req.getParameter("pageNo");
		int pageNo = 1;
		
		if (pageNoVal != null) {
			pageNo = Integer.parseInt(pageNoVal);
		}
		
		RequestPage requestPage = listService.getRequestPage(pageNo);
		req.setAttribute("requestPage", requestPage);
		
		return "listRequest";
	}
	
}
