package ua.nure.serdyuk.db.service;

import ua.nure.serdyuk.entity.TrainInfoBean;

public interface TrainInfoService {

	TrainInfoBean getFullInfo(long trainId, long stFromId, long stToId,
			long routeId);
}
