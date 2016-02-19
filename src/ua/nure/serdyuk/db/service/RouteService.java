package ua.nure.serdyuk.db.service;

import java.sql.Date;
import java.util.List;

import ua.nure.serdyuk.entity.Route;

public interface RouteService {

	boolean create(Route item);

	boolean delete(long id);

	List<Route> getAllByStationsAndDate(long stationFromId, long stationToId,
			Date date);

	List<Route> getAllByDates(String from, String to);

}
