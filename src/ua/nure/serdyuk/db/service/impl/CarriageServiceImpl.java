package ua.nure.serdyuk.db.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import ua.nure.serdyuk.db.dao.CarriageDao;
import ua.nure.serdyuk.db.service.CarriageService;
import ua.nure.serdyuk.entity.Carriage;

public class CarriageServiceImpl implements CarriageService {

	private CarriageDao carriageDao;

	public CarriageServiceImpl(CarriageDao carriageDao) {
		this.carriageDao = carriageDao;
	}

	@Override
	public List<Carriage> getAllByTrainId(long trainId, long routeId) {
		List<Carriage> carriages = carriageDao.getAllByTrainId(trainId,
				routeId);
//		Map<Integer, BigDecimal> prices = carriageDao.getPrice(trainId,
//				carTypes, routeItemFrom, routeItemTo);
		return carriages;
	}

	@Override
	public Map<Integer, BigDecimal> getPrice(long trainId,
			List<Integer> carTypes, long routeItemFrom, long routeItemTo) {
		// TODO Auto-generated method stub
		return carriageDao.getPrice(trainId, carTypes, routeItemFrom,
				routeItemTo);
	}
}
