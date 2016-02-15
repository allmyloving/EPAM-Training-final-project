package ua.nure.serdyuk.db.service.impl;

import java.util.List;

import org.apache.log4j.Logger;

import ua.nure.serdyuk.db.dao.RouteDao;
import ua.nure.serdyuk.db.service.RouteService;
import ua.nure.serdyuk.entity.Route;
import ua.nure.serdyuk.util.DateUtils;

public class RouteServiceMySql implements RouteService {

	private static final Logger LOG = Logger.getLogger(RouteServiceMySql.class);

	private RouteDao routeDao;

	public RouteServiceMySql(RouteDao routeDao) {
		this.routeDao = routeDao;
	}

	@Override
	public List<Route> getAllByStationsAndDate(long stationFromId,
			long stationToId, String date) {
		return routeDao.getAllByStationsAndDate(stationFromId, stationToId,
				DateUtils.extractDate(date));
	}

	@Override
	public boolean create(Route item) {
		return routeDao.create(item);
	}
}
