package review.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.Member;
import member.service.ReadInfoService;
import mvc.command.CommandHandler;
import review.service.ReviewListService;
import review.service.ReviewPage;

public class ReadReviewHandler implements CommandHandler {
	private ReviewListService listService = new ReviewListService();
	private ReadInfoService readService = new ReadInfoService();
		
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		String pageNoVal = req.getParameter("pageNo");
		int pageNo = 1;
		
		if (pageNoVal != null) {
			pageNo = Integer.parseInt(pageNoVal);
		}
		
		String target = req.getParameter("target");
		
		ReviewPage reviewPage = listService.getReviewPage(pageNo, target);
		req.setAttribute("reviewPage", reviewPage);
		
		Member member = readService.read(target);
		req.setAttribute("member", member);
		
		return "listReview";
	}

}
