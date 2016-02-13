package ua.nure.serdyuk.db.dao;

import java.util.List;

import ua.nure.serdyuk.entity.bean.RouteBean;

public interface RouteBeanDao extends GenericDao<RouteBean> {
	
	List<RouteBean> getAllByTrainId(long trainId);

}
