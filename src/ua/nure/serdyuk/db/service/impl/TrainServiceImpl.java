package ua.nure.serdyuk.db.service.impl;

import java.util.List;

import ua.nure.serdyuk.db.dao.TrainDao;
import ua.nure.serdyuk.db.service.TrainService;
import ua.nure.serdyuk.entity.Train;

public class TrainServiceImpl implements TrainService {

	private TrainDao trainDao;

	public TrainServiceImpl(TrainDao trainDao) {
		this.trainDao = trainDao;
	}

	@Override
	public boolean create(Train train) {
		return trainDao.create(train);
	}

	@Override
	public boolean update(Train train) {
		return trainDao.update(train);
	}

	@Override
	public List<Train> getAll() {
		return trainDao.getAll();
	}

	@Override
	public Train get(long id) {
		return trainDao.get(id);
	}

}
