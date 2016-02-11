package ua.nure.serdyuk.db.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

import ua.nure.serdyuk.db.dao.RouteItemDao;
import ua.nure.serdyuk.db.dao.TrainInfoDao;
import ua.nure.serdyuk.db.service.TrainInfoService;
import ua.nure.serdyuk.entity.RouteItem;
import ua.nure.serdyuk.entity.TrainInfoBean;

public class TrainInfoServiceImpl implements TrainInfoService {

	private static final Logger LOG = Logger
			.getLogger(TrainInfoServiceImpl.class);

	private TrainInfoDao trainInfoDao;

	private RouteItemDao routeItemDao;

	public TrainInfoServiceImpl(TrainInfoDao trainInfoDao,
			RouteItemDao routeItemDao) {
		this.trainInfoDao = trainInfoDao;
		this.routeItemDao = routeItemDao;
	}

	@Override
	public TrainInfoBean getFullInfo(long trainId, long stFromId, long stToId,
			long routeId) {
		TrainInfoBean bean = trainInfoDao.getFullInfo(trainId, stFromId, stToId,
				routeId);
		bean.setTrainId(trainId);
		bean.setRouteId(routeId);
		Date depDate = bean.getDepDate();
		Date arrDate = getArrivalDate(bean.getArrDate(), depDate, trainId,
				stFromId, stToId);
		
		bean.setArrDate(arrDate);
		bean.setDuration(getDuration(depDate, arrDate));

		return bean;
	}

	private Date getArrivalDate(Date arrTime, Date depDate, long routeId,
			long stFromId, long stToId) {
		List<RouteItem> routeItems = routeItemDao.getAllByStations(routeId,
				stFromId, stToId);

		Calendar cal = Calendar.getInstance();
		cal.setTime(arrTime);
		Calendar calFrom = Calendar.getInstance();
		calFrom.setTime(depDate);

		cal.set(Calendar.DATE, calFrom.get(Calendar.DATE));
		cal.set(Calendar.MONTH, calFrom.get(Calendar.MONTH));
		cal.set(Calendar.YEAR, calFrom.get(Calendar.YEAR));

		Date arrDate = cal.getTime();
		int days = 0;

		for (int i = 0; i < routeItems.size() - 1; i++) {
			Date dTime = routeItems.get(i).getDepartureTime();
			Date aTime = routeItems.get(i + 1).getArrivalTime();

			if (dTime.after(aTime)) {
				days++;
			}
		}

		if (days > 0) {
			increaseDays(arrDate, days);
		}

		return arrDate;
	}

	private void increaseDays(Date date, int days) {
		LOG.debug(String.format("Days increased by %d", days));

		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, days);
		date.setTime(cal.getTime().getTime());
	}

	private Date getDuration(Date depDate, Date arrDate) {
		Calendar cal = Calendar.getInstance();
		int h = (int) getDateDiff(arrDate, depDate, TimeUnit.HOURS);
		int m = (int) getDateDiff(arrDate, depDate, TimeUnit.MINUTES) - 60 * h;
		cal.set(Calendar.HOUR, h);
		cal.set(Calendar.MINUTE, m);

		return cal.getTime();
	}

	private long getDateDiff(Date date1, Date date2, TimeUnit timeUnit) {
		long diffInMillies = date1.getTime() - date2.getTime();
		return timeUnit.convert(diffInMillies, TimeUnit.MILLISECONDS);
	}
}
