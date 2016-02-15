package ua.nure.serdyuk.db.dao;

import java.math.BigDecimal;

import ua.nure.serdyuk.entity.bean.TrainBean;

public interface TrainBeanDao extends GenericDao<TrainBean> {

	TrainBean getFullInfo(long trainId, long stFromId, long stToId,
			long routeId);
	
	BigDecimal getPrice(long trainId, int carTypeId, int ordinalFrom,
			int ordinalTo);

}
