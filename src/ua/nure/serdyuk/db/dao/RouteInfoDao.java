package ua.nure.serdyuk.db.dao;

import java.util.List;

import ua.nure.serdyuk.entity.TrainInfoBean.RouteInfoBean;

public interface RouteInfoDao extends GenericDao<RouteInfoBean> {
	
	List<RouteInfoBean> getAllByTrainId(long trainId);

}
