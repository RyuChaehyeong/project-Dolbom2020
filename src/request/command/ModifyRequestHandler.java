package request.command;

import java.io.IOException;
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
import request.service.ModifyRequest;
import request.service.ModifyRequestService;
import request.service.PermissionDeniedException;
import request.service.RequestNotFoundException;

public class ModifyRequestHandler implements CommandHandler {
	private static final String FORM_ViEW = "modifyForm";
	private ModifyRequestService modifyService = new ModifyRequestService();

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		if (req.getMethod().equalsIgnoreCase("post")) {
			return processSubmit(req, res);
		} else {
			res.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}

	private String processSubmit(HttpServletRequest req, HttpServletResponse res) throws ParseException, IOException {
		User authUser = (User) req.getSession().getAttribute("authUser");
		String noVal = req.getParameter("reqNo");
		System.out.println(noVal);
		Integer no = Integer.parseInt(noVal);

		Map<String, Boolean> errors = new HashMap<String, Boolean>();
		req.setAttribute("errors", errors);
		
		ModifyRequest modReq = toModifyReq(authUser, no, req, errors);
		req.setAttribute("modReq", modReq);
		
		modReq.validate(errors);

		if (!errors.isEmpty()) {
			return FORM_ViEW;
		}

		try {
			modifyService.modify(modReq);
			res.sendRedirect(req.getContextPath() + "/request/read.do?reqNo=" + no);
			return null;
		} catch (RequestNotFoundException e) {
			res.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;

		} catch (PermissionDeniedException e) {
			res.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}
	}

	private ModifyRequest toModifyReq(User authUser, int no, HttpServletRequest req, Map<String, Boolean> errors) throws ParseException {

		String start = req.getParameter("startDate");
		String end = req.getParameter("endDate");

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

		java.sql.Date sqlStart = new java.sql.Date(startDate.getTime());
		java.sql.Date sqlEnd = new java.sql.Date(endDate.getTime());

		return new ModifyRequest(no, authUser.getMember_id(), req.getParameter("title"), req.getParameter("animal"),
				sqlStart, sqlEnd, req.getParameter("postcode"), req.getParameter("roadaddress"), req.getParameter("info"));
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
