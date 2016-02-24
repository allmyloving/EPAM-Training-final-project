package ua.nure.serdyuk.SummaryTask4.db.service.impl;

import java.util.List;

import org.apache.log4j.Logger;

import ua.nure.serdyuk.SummaryTask4.db.dao.CarriageDao;
import ua.nure.serdyuk.SummaryTask4.db.service.CarriageService;
import ua.nure.serdyuk.SummaryTask4.entity.Carriage;

public class CarriageServiceImpl implements CarriageService {

	private static final Logger LOG = Logger
			.getLogger(CarriageServiceImpl.class);

	private CarriageDao carriageDao;

	public CarriageServiceImpl(CarriageDao carriageDao) {
		super();
		this.carriageDao = carriageDao;
	}

	@Override
	public List<Carriage> getAll(long routeItemFrom, long routeItemTo,
			long trainId, long routeId) {
		return carriageDao.getAllByTrainIdRouteItems(trainId, routeId,
				routeItemFrom, routeItemTo);
	}

	@Override
	public boolean createAll(List<Carriage> carriages) {
		return carriageDao.createAll(carriages);
	}

	@Override
	public List<Carriage> getAll(long trainId) {
		return carriageDao.getAllByTrainId(trainId);
	}
}
