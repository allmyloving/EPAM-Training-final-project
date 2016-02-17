package ua.nure.serdyuk.db.service;

import java.util.List;

import ua.nure.serdyuk.entity.Route;

public interface RouteService {

	boolean create(Route item);

	List<Route> getAllByStationsAndDate(long stationFromId, long stationToId,
			String date);

	List<Route> getAllByDates(String from, String to);

}
