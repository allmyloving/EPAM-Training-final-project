package ua.nure.serdyuk.db.service.impl;

import java.util.List;
import java.util.Map;

import ua.nure.serdyuk.db.dao.CarriageDao;
import ua.nure.serdyuk.db.service.CarriageService;
import ua.nure.serdyuk.entity.Carriage;
import ua.nure.serdyuk.entity.Carriage.CarriageType;

public class CarriageServiceImpl implements CarriageService {

	private CarriageDao carriageDao;

	public CarriageServiceImpl(CarriageDao carriageDao) {
		this.carriageDao = carriageDao;
	}

	@Override
	public List<Carriage> getAll(long routeItemFrom, long routeItemTo,
			long trainId, long routeId) {
		List<Carriage> carriages = carriageDao.getAllByTrainId(trainId,
				routeId);
		Map<Integer, CarriageType> types = carriageDao.getTypes(routeItemFrom,
				routeItemTo, trainId);
		for (Carriage item : carriages) {
			item.setType(types.get(item.getCarTypeId()));
		}
		return carriages;
	}
}
