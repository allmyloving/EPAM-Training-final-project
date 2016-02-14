package ua.nure.serdyuk.db.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import ua.nure.serdyuk.db.dao.CarriageDao;
import ua.nure.serdyuk.db.service.CarriageService;
import ua.nure.serdyuk.entity.Carriage;
import ua.nure.serdyuk.entity.Carriage.CarriageType;

public class CarriageServiceImpl implements CarriageService {

	private static final Logger LOG = Logger
			.getLogger(CarriageServiceImpl.class);

	private CarriageDao carriageDao;

	public CarriageServiceImpl(CarriageDao carriageDao) {
		this.carriageDao = carriageDao;
	}

	@Override
	public List<Carriage> getAll(long routeItemFrom, long routeItemTo,
			long trainId, long routeId) {
		List<Carriage> carriages = carriageDao.getAllByTrainId(trainId, routeId,
				routeItemFrom, routeItemTo);
		Map<Integer, CarriageType> types = carriageDao.getTypes(routeItemFrom,
				routeItemTo, trainId);

		List<Integer> list = null;
		Map<Integer, Boolean> seats = null;
		for (Carriage item : carriages) {
			item.setType(types.get(item.getCarTypeId()));
			list = item.getSeatsTaken();
			seats = new HashMap<Integer, Boolean>();
			
			for (int k : list) {
				seats.put(k, true);
			}
			item.setSeats(seats);
		}
		return carriages;
	}
}
