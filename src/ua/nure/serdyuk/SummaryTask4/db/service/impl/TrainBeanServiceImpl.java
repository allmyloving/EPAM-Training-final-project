package ua.nure.serdyuk.SummaryTask4.db.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import ua.nure.serdyuk.SummaryTask4.db.dao.RouteItemDao;
import ua.nure.serdyuk.SummaryTask4.db.dao.TrainBeanDao;
import ua.nure.serdyuk.SummaryTask4.db.service.TrainBeanService;
import ua.nure.serdyuk.SummaryTask4.entity.RouteItem;
import ua.nure.serdyuk.SummaryTask4.entity.bean.TrainBean;
import ua.nure.serdyuk.SummaryTask4.util.DateUtils;

public class TrainBeanServiceImpl implements TrainBeanService {

	private static final Logger LOG = Logger
			.getLogger(TrainBeanServiceImpl.class);

	private TrainBeanDao trainInfoDao;

	private RouteItemDao routeItemDao;

	public TrainBeanServiceImpl(TrainBeanDao trainInfoDao,
			RouteItemDao routeItemDao) {
		this.trainInfoDao = trainInfoDao;
		this.routeItemDao = routeItemDao;
	}

	@Override
	public TrainBean getFullInfo(long trainId, long stFromId, long stToId,
			long routeId) {
		TrainBean bean = trainInfoDao.getFullInfo(trainId, stFromId, stToId,
				routeId);
		bean.setTrainId(trainId);
		bean.setRouteId(routeId);

		List<RouteItem> routeItems = routeItemDao.getAllByStations(trainId,
				stFromId, stToId);
		Date arrDate = DateUtils.getArrivalDate(bean.getArrDate(),
				bean.getDepDate(), routeItems);

		bean.setArrDate(arrDate);
		bean.setDuration(DateUtils.getDuration(bean.getDepDate(), arrDate));

		return bean;
	}

	@Override
	public List<TrainBean> getAll() {
		return trainInfoDao.getAll();
	}
}
