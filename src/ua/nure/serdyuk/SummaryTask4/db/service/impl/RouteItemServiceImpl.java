package ua.nure.serdyuk.SummaryTask4.db.service.impl;

import java.util.List;

import ua.nure.serdyuk.SummaryTask4.db.dao.RouteItemDao;
import ua.nure.serdyuk.SummaryTask4.db.service.RouteItemService;
import ua.nure.serdyuk.SummaryTask4.entity.RouteItem;

public class RouteItemServiceImpl implements RouteItemService {

	private RouteItemDao routeItemDao;

	public RouteItemServiceImpl(RouteItemDao routeItemDao) {
		this.routeItemDao = routeItemDao;
	}

	@Override
	public List<RouteItem> getAll() {
		return routeItemDao.getAll();
	}

	@Override
	public RouteItem getByTrainAndStation(long trainId, long stationId) {
		return routeItemDao.getByTrainAndStation(trainId, stationId);
	}
}
