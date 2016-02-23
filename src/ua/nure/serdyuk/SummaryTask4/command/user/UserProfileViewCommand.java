package ua.nure.serdyuk.SummaryTask4.command.user;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.serdyuk.SummaryTask4.command.Command;
import ua.nure.serdyuk.SummaryTask4.constants.Const;
import ua.nure.serdyuk.SummaryTask4.constants.Path;
import ua.nure.serdyuk.SummaryTask4.db.service.TicketService;
import ua.nure.serdyuk.SummaryTask4.entity.User;
import ua.nure.serdyuk.SummaryTask4.entity.bean.TicketOrderBean;

public class UserProfileViewCommand implements Command {

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		User currentUser = (User) req.getSession()
				.getAttribute(Const.CURRENT_USER);

		TicketService ticketService = (TicketService) req.getServletContext()
				.getAttribute(Const.TICKET_SERVICE);
		List<TicketOrderBean> beans = ticketService
				.getAllByUserId(currentUser.getId());
		
		req.getSession().setAttribute(Const.TICKETS, beans);
		
		return Path.PROFILE_VIEW;
	}

}
