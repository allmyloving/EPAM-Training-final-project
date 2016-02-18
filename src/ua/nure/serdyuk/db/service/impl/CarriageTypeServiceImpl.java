package ua.nure.serdyuk.db.service.impl;

import java.util.List;

import ua.nure.serdyuk.db.dao.CarriageTypeDao;
import ua.nure.serdyuk.db.service.CarriageTypeService;
import ua.nure.serdyuk.entity.CarriageType;

public class CarriageTypeServiceImpl implements CarriageTypeService {

	private CarriageTypeDao carriageTypeDao;

	public CarriageTypeServiceImpl(CarriageTypeDao carriageTypeDao) {
		this.carriageTypeDao = carriageTypeDao;
	}

	@Override
	public List<CarriageType> getAll() {
		return carriageTypeDao.getAll();
	}

}
