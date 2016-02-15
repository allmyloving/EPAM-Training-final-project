package ua.nure.serdyuk.db.dao;

import java.util.List;

import ua.nure.serdyuk.entity.Ticket;

public interface TicketDao extends GenericDao<Ticket> {

	List<Ticket> getAllByUserId(long userId);

}
