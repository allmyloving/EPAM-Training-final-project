package ua.nure.serdyuk.db.service.impl;

import java.util.List;

import ua.nure.serdyuk.db.dao.RouteInfoDao;
import ua.nure.serdyuk.db.service.RouteInfoService;
import ua.nure.serdyuk.entity.TrainInfoBean.RouteInfoBean;

public class RouteInfoServiceImpl implements RouteInfoService {
	
	private RouteInfoDao routeInfoDao;
	
	public RouteInfoServiceImpl(RouteInfoDao routeInfoDao) {
		this.routeInfoDao = routeInfoDao;
	}

	@Override
	public List<RouteInfoBean> getAllByTrainId(long trainId) {
		return routeInfoDao.getAllByTrainId(trainId);
	}

}
