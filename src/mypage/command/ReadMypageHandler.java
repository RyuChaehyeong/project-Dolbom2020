package mypage.command;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import auth.service.User;
import jdbc.ConnectionProvider;
import mvc.command.CommandHandler;
import mypage.service.ContractPage;
import mypage.service.ListContractService;
import mypage.service.ListQuoteToCustomerService;
import mypage.service.ListRequestOfCustomerService;
import mypage.service.QuotePage;
import request.dao.RequestDao;
import request.service.RequestPage;

public class ReadMypageHandler implements CommandHandler{
	ListRequestOfCustomerService listReqService = new ListRequestOfCustomerService();
	ListQuoteToCustomerService listQuoService = new ListQuoteToCustomerService();
	ListContractService listConService = new ListContractService();
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		User user = (User) req.getSession().getAttribute("authUser");
		String userId = user.getMember_id();
		
		//customer의 mypage 구성: 작성한 요청서, 받은 견적서, 이용내역
		if (user.getStatus() == 0) {
			
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
			String contractPageNo = req.getParameter("cpageNo");
			int cpageNo = 1;
			if (contractPageNo != null) {
				cpageNo = Integer.parseInt(contractPageNo);
			}
			ContractPage contractPage = listConService.getContractCustomer(cpageNo, userId);
			req.setAttribute("contractPage", contractPage);
			
			return "myPageCustomer";
		
			
		//provider의 mypage 구성: 보낸 견적서, 이용내역
		} else if (user.getStatus() == 1) {
			
			//내가 받은 견적서
			String quotePageVal = req.getParameter("qpageNo");
			int qpageNo = 1;
			if (quotePageVal != null) {
				qpageNo = Integer.parseInt(quotePageVal);
			}
			
			QuotePage quotePage = listQuoService.getQuoteFromProvider(qpageNo, userId);
			req.setAttribute("quotePage", quotePage);
			
			//나의 돌봄 이용 내역
			String contractPageNo = req.getParameter("cpageNo");
			int cpageNo = 1;
			if (contractPageNo != null) {
				cpageNo = Integer.parseInt(contractPageNo);
			}
			
			ContractPage contractPage = listConService.getContractProvider(cpageNo, userId);
			req.setAttribute("contractPage", contractPage);
			
			return "myPageProvider";
			
		} else {
			
			return null;
		}
		
	}

}
