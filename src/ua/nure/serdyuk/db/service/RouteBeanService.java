package ua.nure.serdyuk.db.service;

import java.util.List;

import ua.nure.serdyuk.entity.bean.RouteBean;

public interface RouteBeanService {

	List<RouteBean> getAllByTrainId(long trainId);

}
