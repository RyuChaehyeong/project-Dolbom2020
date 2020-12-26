package quote.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import quote.service.Quote;
import quote.service.QuoteNotFoundException;
import quote.service.ReadQuoteService;

public class ReadQuoteHandler implements CommandHandler{
	ReadQuoteService readService = new ReadQuoteService();

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		String noVal = req.getParameter("no");
		int  quoteNo = Integer.parseInt(noVal);
		
		try {
			Quote quote = readService.getQuote(quoteNo);
			req.setAttribute("quote", quote);
			return "readQuote";
		} catch (QuoteNotFoundException e) {
			req.getServletContext().log("no quote", e);
			res.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}
	}

}
