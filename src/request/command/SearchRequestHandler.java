package request.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import request.service.RequestPage;
import request.service.SearchRequest;
import request.service.SearchRequestService;

public class SearchRequestHandler implements CommandHandler {
	SearchRequestService searchService = new SearchRequestService();

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		String pageNoVal = req.getParameter("pageNo");
		int pageNo = 1;
		
		if (pageNoVal != null) {
			pageNo = Integer.parseInt(pageNoVal);
		}
		
		String field = req.getParameter("field");
		String word = req.getParameter("word");
		
		SearchRequest searchReq = new SearchRequest(field, word);

		RequestPage requestPage = searchService.search(pageNo, searchReq);
		req.setAttribute("requestPage", requestPage);
		req.setAttribute("total", requestPage.getTotalReq());
		
		return "searchRequest";
	}

}
