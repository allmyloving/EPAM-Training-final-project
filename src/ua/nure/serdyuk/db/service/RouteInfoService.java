package ua.nure.serdyuk.db.service;

import java.util.List;

import ua.nure.serdyuk.entity.bean.TrainBean.RouteInfo;

public interface RouteInfoService {

	List<RouteInfo> getAllByTrainId(long trainId);

}
