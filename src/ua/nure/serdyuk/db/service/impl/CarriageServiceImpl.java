package ua.nure.serdyuk.db.service.impl;

import java.util.List;

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
		return carriageDao.getAllByTrainId(trainId, routeId);
	}
}
