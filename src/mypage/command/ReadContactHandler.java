package mypage.command;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.Member;
import member.service.MemberNotFoundException;
import member.service.ReadInfoService;
import mvc.command.CommandHandler;

public class ReadContactHandler implements CommandHandler {
	private static final String FORM_VIEW = "contactInfo";
	ReadInfoService readInfoService = new ReadInfoService();
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		
		String memberId = req.getParameter("memberId");
		if (memberId == null || memberId.isEmpty()) {
			throw new MemberNotFoundException();
		}
		
		try {
			Member member = readInfoService.read(memberId);
			req.setAttribute("member", member);
			return FORM_VIEW;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
		
		
	}

}
