package ua.nure.serdyuk.db.service.impl;

import java.util.List;

import org.apache.log4j.Logger;

import ua.nure.serdyuk.constants.Const;
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
				DateUtils.extractDate(date, Const.CLIENT_DATE_FORMAT));
	}

	@Override
	public boolean create(Route item) {
		return routeDao.create(item);
	}

	@Override
	public List<Route> getAllByDates(String from, String to) {
		return routeDao.getAllByDates(
				DateUtils.extractDate(from, Const.CLIENT_DATE_FORMAT),
				DateUtils.extractDate(to, Const.CLIENT_DATE_FORMAT));
	}

	@Override
	public boolean delete(long id) {
		return routeDao.delete(id);
	}
}
