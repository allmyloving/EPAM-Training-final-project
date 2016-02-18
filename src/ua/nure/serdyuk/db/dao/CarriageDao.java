package ua.nure.serdyuk.db.dao;

import java.util.List;

import ua.nure.serdyuk.entity.Carriage;

public interface CarriageDao extends GenericDao<Carriage> {
	
	boolean createAll(List<Carriage> carriages);

	List<Carriage> getAllByTrainId(long trainId, long routeId,
			long routeItemFrom, long routeItemTo);

}
