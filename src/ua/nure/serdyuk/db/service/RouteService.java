package ua.nure.serdyuk.db.service;

import java.util.List;

import ua.nure.serdyuk.entity.Route;

public interface RouteService {

	List<Route> getAllByStationsAndDate(long stationFromId, long stationToId,
			String date);

}
