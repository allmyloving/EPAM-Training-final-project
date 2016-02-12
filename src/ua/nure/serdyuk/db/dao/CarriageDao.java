package ua.nure.serdyuk.db.dao;

import java.util.List;
import java.util.Map;

import ua.nure.serdyuk.entity.Carriage;
import ua.nure.serdyuk.entity.Carriage.CarriageType;

public interface CarriageDao extends GenericDao<Carriage> {

	List<Carriage> getAllByTrainId(long trainId, long routeId);
	
	public Map<Integer, CarriageType> getTypes(long routeItemFrom,
			long routeItemTo, long trainId);

}
