package ua.nure.serdyuk.SummaryTask4.command.train;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.serdyuk.SummaryTask4.command.Command;
import ua.nure.serdyuk.SummaryTask4.constants.Const;
import ua.nure.serdyuk.SummaryTask4.constants.Path;
import ua.nure.serdyuk.SummaryTask4.db.service.RouteItemService;
import ua.nure.serdyuk.SummaryTask4.db.service.TicketService;
import ua.nure.serdyuk.SummaryTask4.entity.Carriage;
import ua.nure.serdyuk.SummaryTask4.entity.RouteItem;
import ua.nure.serdyuk.SummaryTask4.entity.Ticket;
import ua.nure.serdyuk.SummaryTask4.entity.User;
import ua.nure.serdyuk.SummaryTask4.entity.bean.TicketOrderBean;
import ua.nure.serdyuk.SummaryTask4.entity.bean.TrainBean;

public class OrderTicketCommand implements Command {

	private static final Logger LOG = org.apache.log4j.Logger
			.getLogger(OrderTicketCommand.class);

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res) {
		String firstName = req.getParameter("firstName");
		String lastName = req.getParameter("lastName");

		HttpSession session = req.getSession();
		User currentUser = (User) session.getAttribute(Const.CURRENT_USER);

		if (currentUser == null) {
			// throw new AppException("Login first.");
			session.setAttribute("loginError", "message.login_first");

			return Path.LOGIN_VIEW_COMMAND;
		}

		TicketOrderBean bean = (TicketOrderBean) session
				.getAttribute(Const.TICKET_ORDER_BEAN);
		if (bean == null) {
			LOG.error("Bean is null");
			bean = (TicketOrderBean) req.getAttribute(Const.TICKET_ORDER_BEAN);
		}
		bean.setFirstName(firstName);
		bean.setLastName(lastName);

		LOG.info(String.format("firstName=%s, lastName=%s", firstName,
				lastName));
		RouteItemService itemService = (RouteItemService) req
				.getServletContext().getAttribute(Const.ROUTE_ITEM_SERVICE);

		Ticket ticket = prepareTicket(itemService, firstName, lastName,
				currentUser.getId(), bean);
		TicketService service = (TicketService) req.getServletContext()
				.getAttribute(Const.TICKET_SERVICE);
		if (!service.exists(bean)) {
			if (service.create(ticket)) {
				LOG.info("Ticket successfully created");
				bean.setTicketId(ticket.getId());
			} else {
				LOG.error("Failed to create ticket");
			}
		} else {
			LOG.error("Seat is alredy taken");
		}

		return Path.PAYMENT_SUCCESSFUL_VIEW_COMMAND;
	}

	private Ticket prepareTicket(RouteItemService itemService, String firstName,
			String lastName, long userId, TicketOrderBean bean) {
		Carriage carriage = bean.getCarriage();
		TrainBean trainBean = bean.getTrainBean();
		long trainId = bean.getTrainBean().getTrainId();
		RouteItem from = itemService.getByTrainAndStation(trainId,
				bean.getStationFrom().getId());
		RouteItem to = itemService.getByTrainAndStation(trainId,
				bean.getStationTo().getId());

		Ticket ticket = new Ticket();
		ticket.setFirstName(firstName);
		ticket.setLastName(lastName);
		ticket.setCarriageId(carriage.getId());
		ticket.setPrice(carriage.getPrice());
		ticket.setDepDate(trainBean.getDepDate());
		ticket.setArrDate(trainBean.getArrDate());
		ticket.setRouteDepId(from.getId());
		ticket.setRouteArrId(to.getId());
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
