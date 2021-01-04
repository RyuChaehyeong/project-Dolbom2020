package review.command;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import auth.service.User;
import mvc.command.CommandHandler;
import quote.service.Quote;
import quote.service.QuoteNotFoundException;
import quote.service.ReadQuoteService;
import request.service.ReadRequestService;
import request.service.Request;
import review.service.WriteReview;
import review.service.WrtieReviewService;

public class WriteReviewHandler implements CommandHandler {
	private static final String FORM_VIEW = "newReviewForm";
	private ReadQuoteService readQuoService = new ReadQuoteService();
	private ReadRequestService readReqService = new ReadRequestService();
	private WrtieReviewService writeService = new WrtieReviewService();
	
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

	private String processForm(HttpServletRequest req, HttpServletResponse res) {
		String quoNumVal = req.getParameter("quoNum");
		int quoNum = 0;
		if (quoNumVal != null) {
			quoNum = Integer.parseInt(quoNumVal);
		}

		Quote quote = readQuoService.getQuote(quoNum);
		if (quote == null) {
			throw new QuoteNotFoundException();
		}
		req.setAttribute("quote", quote);
		return FORM_VIEW;
	}

	private String processSubmit(HttpServletRequest req, HttpServletResponse res) throws IOException, SQLException {
		Map<String, Boolean> errors = new HashMap<String, Boolean>();
		req.setAttribute("errors", errors);
		
		User user = (User) req.getSession().getAttribute("authUser");
		String reviewer = user.getMember_id();
		
		//queryString으로 부터 가져올 내용
		String quoNumVal = req.getParameter("quoNum");
		int quoNum = 0;
		if (quoNumVal != null) {
			quoNum = Integer.parseInt(quoNumVal);
		}

		Quote quote = readQuoService.getQuote(quoNum);
		if (quote == null) {
			throw new QuoteNotFoundException();
		}
		
		
		
		//form으로 부터 받아올 내용
		String scoreVal = req.getParameter("score");
		int score = 0;
		if (scoreVal != null) {
			score = Integer.parseInt(scoreVal);			
		}

		String comment = req.getParameter("comment");
		
		
		//writeReview
		WriteReview writeRv = null;
		
		//일반회원일 때는 quoNum으로 돌봄이를 평가
		if (user.getStatus()==0) {					
			writeRv = new WriteReview(
					quote.getReqSum().getReqNo(), 
					quoNum, 
					score, 
					comment, 
					reviewer, 
					quote.getProvider());
			
		//돌봄이 회원은 reqNum으로 고객을 평가
		} else if (user.getStatus()==1) {
			writeRv = new WriteReview(
					quote.getReqSum().getReqNo(),
					quoNum, 
					score, 
					comment, 
					reviewer,
					quote.getReqSum().getReqWriter());

		}
		
		writeRv.validate(errors);
		
		if (!errors.isEmpty()) {
			return FORM_VIEW;
		}
		
		int newReviewNo = writeService.write(writeRv);
		req.setAttribute("newReviewNo", newReviewNo);
		res.sendRedirect(req.getContextPath()+"/mypage/read.do");
		
		return null;
	}

}
