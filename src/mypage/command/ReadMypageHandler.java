package mypage.command;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import auth.service.User;
import jdbc.ConnectionProvider;
import mvc.command.CommandHandler;
import mypage.service.ListQuoteToCustomerService;
import mypage.service.ListRequestOfCustomerService;
import mypage.service.QuotePage;
import request.dao.RequestDao;
import request.service.RequestPage;

public class ReadMypageHandler implements CommandHandler{
	ListRequestOfCustomerService listReqService = new ListRequestOfCustomerService();
	ListQuoteToCustomerService listQuoService = new ListQuoteToCustomerService();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		User user = (User) req.getSession().getAttribute("authUser");
		String userId = user.getMember_id();
		if (user.getStatus().equals("0")) {
			
			//돌봄요청서
			String pageNoVal = req.getParameter("pageNo");
			int pageNo = 1;
			
			if (pageNoVal != null) {
				pageNo = Integer.parseInt(pageNoVal);
			}
			RequestPage requestPage = listReqService.getRequestPageOfCustomer(pageNo, userId);
			req.setAttribute("requestPage", requestPage);

			//내가 받은 견적서
			String quotePageVal = req.getParameter("qpageNo");
			int qpageNo = 1;
			if (quotePageVal != null) {
				qpageNo = Integer.parseInt(quotePageVal);
			}
			
			QuotePage quotePage = listQuoService.getQuoteToCustomer(qpageNo, userId);
			req.setAttribute("quotePage", quotePage);
			
			
			//나의 돌봄 이용 내역
			
			
			return "myPageCustomer";
			
		} else if (user.getStatus().equals("1")) {
			
			//내가 받은 견적서
			String quotePageVal = req.getParameter("qpageNo");
			int qpageNo = 1;
			if (quotePageVal != null) {
				qpageNo = Integer.parseInt(quotePageVal);
			}
			
			QuotePage quotePage = listQuoService.getQuoteFromProvider(qpageNo, userId);
			req.setAttribute("quotePage", quotePage);
			return "myPageProvider";
			
		} else {
			
			return null;
		}
		
	}

}
