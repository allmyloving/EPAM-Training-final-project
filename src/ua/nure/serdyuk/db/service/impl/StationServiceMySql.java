package ua.nure.serdyuk.db.service.impl;

import java.util.List;

import ua.nure.serdyuk.db.dao.StationDao;
import ua.nure.serdyuk.db.service.StationService;
import ua.nure.serdyuk.entity.Station;

public class StationServiceMySql implements StationService {

	// private static final Logger LOG = Logger
	// .getLogger(StationServiceMySql.class);

	private StationDao stationDao;

	public StationServiceMySql(StationDao stationDao) {
		this.stationDao = stationDao;
	}

	@Override
	public List<Station> getAll() {
		return stationDao.getAll(null);
	}

	@Override
	public List<Station> getAll(String filter) {
		return stationDao.getAll(filter);
	}

	@Override
	public Station getByName(String name) {
		return stationDao.getByName(name);
	}

	@Override
	public List<Station> getByRouteItems(long routeItemId1, long routeItemId2) {
		return stationDao.getByRouteItems(routeItemId1, routeItemId2);
	}
}
