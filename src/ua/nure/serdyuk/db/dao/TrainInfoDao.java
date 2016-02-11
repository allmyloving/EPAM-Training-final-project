package ua.nure.serdyuk.db.dao;

import java.math.BigDecimal;

import ua.nure.serdyuk.entity.TrainInfoBean;

public interface TrainInfoDao extends GenericDao<TrainInfoBean> {

	TrainInfoBean getFullInfo(long trainId, long stFromId, long stToId,
			long routeId);
	
	BigDecimal getPrice(long trainId, int carTypeId, int ordinalFrom,
			int ordinalTo);

}
