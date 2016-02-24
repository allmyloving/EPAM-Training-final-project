package ua.nure.serdyuk.SummaryTask4.db.service;

import java.util.List;

import ua.nure.serdyuk.SummaryTask4.entity.Ticket;
import ua.nure.serdyuk.SummaryTask4.entity.bean.TicketOrderBean;

public interface TicketService {

	boolean create(Ticket ticket);

	List<TicketOrderBean> getAllByUserId(long userId);
	
	boolean exists(TicketOrderBean bean);
}
