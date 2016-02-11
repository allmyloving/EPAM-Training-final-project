package ua.nure.serdyuk.db.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.log4j.Logger;

import ua.nure.serdyuk.constants.Const;
import ua.nure.serdyuk.db.dao.RouteDao;
import ua.nure.serdyuk.db.service.RouteService;
import ua.nure.serdyuk.entity.Route;
import ua.nure.serdyuk.exception.AppException;

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
				getDate(date));
	}

	private java.sql.Date getDate(String date){
		SimpleDateFormat format = new SimpleDateFormat(
				Const.CLIENT_DATE_FORMAT);
		java.util.Date parsed;
		try {
			parsed = format.parse(date);
		} catch (ParseException e) {
			LOG.error(e.getMessage());
			throw new AppException(e.getMessage());
		}
		
		return new java.sql.Date(parsed.getTime());
	}

}
