package ua.nure.serdyuk.db.service;

import ua.nure.serdyuk.entity.bean.TrainBean;

public interface TrainBeanService {

	TrainBean getFullInfo(long trainId, long stFromId, long stToId,
			long routeId);
}