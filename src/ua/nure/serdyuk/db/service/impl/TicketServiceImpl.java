package ua.nure.serdyuk.db.service.impl;

import ua.nure.serdyuk.db.dao.TicketDao;
import ua.nure.serdyuk.db.service.TicketService;
import ua.nure.serdyuk.entity.Ticket;

public class TicketServiceImpl implements TicketService {

	private TicketDao ticketDao;
	
	public TicketServiceImpl(TicketDao ticketDao) {
		this.ticketDao = ticketDao;
	}

	@Override
	public boolean create(Ticket ticket) {
		return ticketDao.create(ticket);
	}

}
