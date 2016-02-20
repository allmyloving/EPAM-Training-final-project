package ua.nure.serdyuk.db.service.impl;

import java.util.List;

import ua.nure.serdyuk.db.dao.RouteItemDao;
import ua.nure.serdyuk.db.service.RouteItemService;
import ua.nure.serdyuk.entity.RouteItem;

public class RouteItemServiceMySql implements RouteItemService {

	private RouteItemDao routeItemDao;

	public RouteItemServiceMySql(RouteItemDao routeItemDao) {
		this.routeItemDao = routeItemDao;
	}

	@Override
	public List<RouteItem> getAll() {
		return routeItemDao.getAll();
	}
}
