package request.command;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import auth.service.User;
import mvc.command.CommandHandler;
import request.model.Writer;
import request.service.WriteRequestService;
import request.service.WriterRequest;

public class WriteRequestHandler implements CommandHandler {
	private static final String FORM_VIEW = "newRequestForm";
	private WriteRequestService writeService = new WriteRequestService();

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

	private String processSubmit(HttpServletRequest req, HttpServletResponse res) throws Exception {

		Map<String, Boolean> errors = new HashMap<String, Boolean>();
		req.setAttribute("errors", errors);

		User user = (User) req.getSession().getAttribute("authUser");

		
		WriterRequest writeReq = createWriteRequest(user, req, errors);

		writeReq.validate(errors);

		if (!errors.isEmpty()) {
			return FORM_VIEW;
		}

		int newArticleNo = writeService.write(writeReq);
		req.setAttribute("newArticleNo", newArticleNo);
		res.sendRedirect(req.getContextPath() + "/request/list.do");
		return null;
	}

	private WriterRequest createWriteRequest(User user, HttpServletRequest req, Map<String, Boolean> errors)
			throws ParseException {
		String start = req.getParameter("startDate");
		String end = req.getParameter("endDate");
		//입력여부 테스트
		if (start.isEmpty()) {
			errors.put("startDate", Boolean.TRUE);
			start = todayDate();
		}
		if (end.isEmpty()) {
			errors.put("endDate", Boolean.TRUE);
			end = todayDate();
		}

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date startDate = sdf.parse(start);
		Date endDate = sdf.parse(end);
		Date todayDate = sdf.parse(todayDate());

		//오늘보다 늦은 날인지 테스트
		if (!laterThantoday(startDate, todayDate)) {
			errors.put("earlystart", Boolean.TRUE);
			startDate = todayDate;
		}
		
		if (!laterThantoday(endDate, todayDate)) {
			errors.put("earlyend", Boolean.TRUE);
			endDate = todayDate;
		}

		//끝나는 날이 더 늦은지 테스트
		if (!endIsAfter(startDate, endDate)) {
			errors.put("endIsBefore", Boolean.TRUE);
			startDate = todayDate;
			endDate = todayDate;
		}

		return new WriterRequest(new Writer(user.getMember_id(), user.getName()), req.getParameter("title"),
				req.getParameter("animal"), startDate, endDate, req.getParameter("postcode"), req.getParameter("roadAddress"),req.getParameter("info"));
	}

	private boolean endIsAfter(Date startDate, Date endDate) {
		return endDate.after(startDate);
	}

	private boolean laterThantoday(Date startDate, Date todayDate) {
		return startDate.after(todayDate);
	}

	private String todayDate() {
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH) + 1;
		int date = cal.get(Calendar.DATE);
		return year + "-" + month + "-" + date;
	}

}
