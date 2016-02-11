package ua.nure.serdyuk.db.service;

import java.util.List;

import ua.nure.serdyuk.entity.TrainInfoBean.RouteInfoBean;

public interface RouteInfoService {

	List<RouteInfoBean> getAllByTrainId(long trainId);

}
