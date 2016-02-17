package ua.nure.serdyuk.command.train;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.serdyuk.command.Command;
import ua.nure.serdyuk.constants.Const;
import ua.nure.serdyuk.constants.Path;
import ua.nure.serdyuk.db.service.TicketService;
import ua.nure.serdyuk.entity.Carriage;
import ua.nure.serdyuk.entity.Ticket;
import ua.nure.serdyuk.entity.User;
import ua.nure.serdyuk.entity.bean.TicketOrderBean;
import ua.nure.serdyuk.entity.bean.TrainBean;

public class OrderTicketCommand implements Command {

	private static final Logger LOG = org.apache.log4j.Logger
			.getLogger(OrderTicketCommand.class);

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res) {
		String firstName = req.getParameter("firstName");
		String lastName = req.getParameter("lastName");

		HttpSession session = req.getSession();
		User currentUser = (User) session.getAttribute(Const.CURRENT_USER);
		// move to filter
		if (currentUser == null) {
			// throw new AppException("Login first.");
			req.setAttribute("loginErrors",
					Arrays.asList("message.login_first"));
			return Path.LOGIN_VIEW_COMMAND;
		}

		TicketOrderBean bean = (TicketOrderBean) session
				.getAttribute(Const.TICKET_ORDER_BEAN);
		if (bean == null) {
			LOG.error("Bean is null");
			bean = (TicketOrderBean) req.getAttribute(Const.TICKET_ORDER_BEAN);
		}

		Ticket ticket = prepareTicket(firstName, lastName, currentUser.getId(),
				bean);
		TicketService service = (TicketService) req.getServletContext()
				.getAttribute(Const.TICKET_SERVICE);

		if (service.create(ticket)) {
			LOG.info("Ticket successfully created");
		} else {
			LOG.info("Failed to create ticket");
		}

		return Path.PAYMENT_SUCCESSFUL_VIEW_COMMAND;
	}

	private Ticket prepareTicket(String firstName, String lastName, long userId,
			TicketOrderBean bean) {
		Carriage carriage = bean.getCarriage();
		TrainBean trainBean = bean.getTrainBean();

		Ticket ticket = new Ticket();
		ticket.setFirstName(firstName);
		ticket.setLastName(lastName);
		ticket.setCarriageId(carriage.getId());
		ticket.setPrice(carriage.getType().getPrice());
		ticket.setRouteItemDepId(trainBean.getRouteItemIdFrom());
		ticket.setRouteItemArrId(trainBean.getRouteItemIdTo());
		ticket.setRouteId(trainBean.getRouteId());
		ticket.setSeatNum(bean.getSeatNum());
		ticket.setUserId(userId);

		// will be added later (maybe)
		// ticket.setDiscountTypeId(0);
		// ticket.setStatusId(0);
		LOG.info(String.format("Creating ticket %s", ticket.toString()));

		return ticket;
	}

}
