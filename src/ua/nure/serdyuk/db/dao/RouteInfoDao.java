package ua.nure.serdyuk.db.dao;

import java.util.List;

import ua.nure.serdyuk.entity.bean.TrainBean.RouteInfo;

public interface RouteInfoDao extends GenericDao<RouteInfo> {
	
	List<RouteInfo> getAllByTrainId(long trainId);

}
