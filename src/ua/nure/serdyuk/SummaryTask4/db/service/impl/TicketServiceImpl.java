package ua.nure.serdyuk.SummaryTask4.db.service.impl;

import java.util.List;

import ua.nure.serdyuk.SummaryTask4.db.dao.TicketDao;
import ua.nure.serdyuk.SummaryTask4.db.service.TicketService;
import ua.nure.serdyuk.SummaryTask4.entity.Ticket;
import ua.nure.serdyuk.SummaryTask4.entity.bean.TicketOrderBean;

public class TicketServiceImpl implements TicketService {

	private TicketDao ticketDao;

	public TicketServiceImpl(TicketDao ticketDao) {
		this.ticketDao = ticketDao;
	}

	@Override
	public boolean create(Ticket ticket) {
		return ticketDao.create(ticket);
	}

	@Override
	public List<TicketOrderBean> getAllByUserId(long userId) {
		List<TicketOrderBean> tickets = ticketDao.getAllByUserId(userId);
		return tickets;
	}

	@Override
	public boolean exists(TicketOrderBean bean) {
		return ticketDao.exists(bean);
	}

	@Override
	public List<TicketOrderBean> getAllForTomorrow() {
		return ticketDao.getAllForTomorrow();
	}

	@Override
	public boolean setNotified(long id) {
		return ticketDao.setNotified(id);
	}

}
