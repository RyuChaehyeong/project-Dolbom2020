package quote.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import quote.service.Quote;
import quote.service.QuoteCompleteService;
import quote.service.QuoteNotFoundException;
import quote.service.ReadQuoteService;

public class ChooseQuoteHandler implements CommandHandler{
	ReadQuoteService readService = new ReadQuoteService();
	QuoteCompleteService completeService = new QuoteCompleteService();
	

	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		String noVal = req.getParameter("quoteNo");
		int quoteNo = Integer.parseInt(noVal);
		Quote quote = readService.getQuote(quoteNo);
		if (quote == null) {
			throw new QuoteNotFoundException();
		}
		completeService.completeContract(quoteNo, quote.getReqSum().getReqNo());
		
		return "contractSuccess";
		
	}

}
