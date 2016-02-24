package ua.nure.serdyuk.SummaryTask4.db.dao;

import java.util.List;

import ua.nure.serdyuk.SummaryTask4.entity.Ticket;
import ua.nure.serdyuk.SummaryTask4.entity.bean.TicketOrderBean;

public interface TicketDao extends GenericDao<Ticket> {

	List<TicketOrderBean> getAllByUserId(long userId);

	boolean exists(TicketOrderBean bean);

}
