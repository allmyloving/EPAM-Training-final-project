package ua.nure.serdyuk.db.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import ua.nure.serdyuk.entity.Carriage;

public interface CarriageDao extends GenericDao<Carriage> {

	List<Carriage> getAllByTrainId(long trainId, long routeId);
	
	Map<Integer, BigDecimal> getPrice(long trainId,
			List<Integer> carTypes, long routeItemFrom, long routeItemTo);

}
