package ua.nure.serdyuk.db.service;

import java.util.List;

import ua.nure.serdyuk.entity.RouteItem;

public interface RouteItemService {

	List<RouteItem> getAll();
	
	boolean createAll(List<RouteItem> items);
}
