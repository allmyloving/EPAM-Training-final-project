package ua.nure.serdyuk.db.dao;

import java.util.List;

import ua.nure.serdyuk.entity.Carriage;

public interface CarriageDao extends GenericDao<Carriage> {

	List<Carriage> getAllByTrainId(long trainId, long routeId);

}
